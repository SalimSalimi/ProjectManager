package fr.im.salimi.projectmanager.ui.taskForm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Filter
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import fr.im.salimi.projectmanager.databinding.TaskFormFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.*
import java.util.*
import kotlin.collections.ArrayList

class TaskFormFragment : Fragment() {

    private val args: TaskFormFragmentArgs by navArgs()
    private lateinit var binding: TaskFormFragmentBinding
    private val viewModel: TaskFormViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val dao = database.taskDao()
        TaskFormViewModelFactory(args.taskId, TaskRepository(dao), DeveloperRepository(database.developerDao()))
    }

    private lateinit var spinnerAdapter: DevelopersSpinnerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.task_form_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        val list = mutableListOf<Developer>()

        list.add(Developer(firstName = "Salim", lastName = "Salimi"))
        list.add(Developer(firstName = "Hello", lastName = "Salimi"))
        list.add(Developer(firstName = "World", lastName = "Salimi"))

        initSpinner()
        initObservers()
        setFabBtnBehaviour(FabButtonStates.SECONDARY_STATE) {
            fabBtnClicked()
        }
    }

    private fun initObservers() {
        viewModel.dateClickEvent.observe(viewLifecycleOwner) {
            if (it)
                onDateChooser()
        }

        viewModel.addFabBtnClickEvent.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.upsert()
                viewModel.onAddFabClickedEventFinished()
                this.findNavController().navigate(R.id.action_taskFormFragment_to_taskListFragment)
            }
        }

        viewModel.developersList.observe(viewLifecycleOwner) { developers ->
            spinnerAdapter.setEntitiesList(developers)
        }
    }

    private fun initSpinner() {
        spinnerAdapter = DevelopersSpinnerAdapter(mutableListOf())
        with(binding.editTextTaskDevelopers) {
            setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
            threshold = 2
            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val developer = spinnerAdapter.getItem(position) as Developer
                setText("")
                addChip(developer)
            }
            setAdapter(spinnerAdapter)
        }
    }

    private fun onDateChooser() {
        val initValues = Pair<Long, Long>(
            viewModel.task.value?.startingDate?.time,
            viewModel.task.value?.endingDate?.time
        )
        chooseDatePicker(initValues, {
            val startingDate = Date(it.first ?: Date().time)
            val endingDate = Date(it.second ?: Date().time)
            viewModel.onChooseDate(startingDate, endingDate)
            viewModel.onDateClickedEventFinished()
            binding.invalidateAll()
        }, {
            viewModel.onDateClickedEventFinished()
        }, {
            viewModel.onDateClickedEventFinished()
        })
    }

    private fun addChip(developer: Developer) {
        val chip = Chip(requireContext())
        chip.text = developer.firstName

        chip.isCheckable = false
        chip.isClickable = false
        chip.isCloseIconVisible = true

        chip.setChipIconResource(R.drawable.ic_baseline_person_24)
        binding.developersChipGroup.addView(chip)

        chip.setOnCloseIconClickListener {
            Log.d("TaskFormFragment", "Closed")
            binding.developersChipGroup.removeView(chip)
        }
    }

    private fun fabBtnClicked() {
        viewModel.onAddFabBtnClickEvent()
    }

    inner class DevelopersSpinnerAdapter(developersList: List<Developer>) : BaseSpinnerAdapter<Developer>(requireContext(), developersList) {

        internal var tempItems: MutableList<Developer> = mutableListOf()
        internal var suggestions: MutableList<Developer> = mutableListOf()

        init {
            tempItems = getEntitiesList().toMutableList()
            suggestions = ArrayList()
        }

        override fun onSetViews(item: Developer, titleView: TextView, subTitleView: TextView, roundedLetter: TextView) {
            titleView.text = item.firstName
            subTitleView.text = item.post.name
            roundedLetter.text = item.firstName[0].toString()
            roundedLetter.setBackgroundColorText(item.firstName)
        }

        private var customFilter: Filter = object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                return if (constraint != null) {
                    suggestions.clear()
                    tempItems.forEach { developer ->  
                        if (developer.firstName.toLowerCase(Locale.getDefault()).contains(constraint.toString().toLowerCase(Locale.getDefault())))
                            suggestions.add(developer)
                    }
                    filterResults.values = suggestions
                    filterResults.count = suggestions.size
                    filterResults
                } else {
                    filterResults.values = tempItems
                    filterResults.count = tempItems.size
                    filterResults
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                val filterList = results.values as? List<Developer>
                if (results.count > 0) {
                    clear()
                    filterList?.forEach {
                        add(it)
                    }.also {
                        notifyDataSetChanged()
                    }
                }
            }

        }

        override fun setEntitiesList(data: List<Developer>) {
            super.setEntitiesList(data)
            tempItems = getEntitiesList().toMutableList()
        }

        override fun getFilter(): Filter {
            return customFilter
        }
    }
}