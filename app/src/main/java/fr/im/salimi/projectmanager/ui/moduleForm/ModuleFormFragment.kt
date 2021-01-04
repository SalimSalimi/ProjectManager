package fr.im.salimi.projectmanager.ui.moduleForm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.databinding.ModuleFormFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.*

class ModuleFormFragment : Fragment() {

    private val args: ModuleFormFragmentArgs by navArgs()
    private lateinit var binding: ModuleFormFragmentBinding
    private val viewModel: ModuleFormViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val repository = ModuleRepository(database.moduleDao())
        ModuleFormViewModelFactory(args.moduleId, repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.module_form_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setFabBtnBehaviour(FabButtonStates.SECONDARY_STATE) {
            viewModel.onAddBtnClicked()
        }
        binding.viewModel = viewModel

        initObservers()
    }

    private fun initObservers() {
        viewModel.dateClickEvent.observe(viewLifecycleOwner) {
            if (it) {
                showDatePicker()
            }
        }

        viewModel.addBtnClickEvent.observe(viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(R.id.action_moduleFormFragment_to_moduleListFragment)
                viewModel.onAddBtnClickedFinished()
            }
        }
    }

    private fun showDatePicker() {
        val initPair = Pair(viewModel.module.value!!.startingDate.time,
                viewModel.module.value!!.endingDate.time)

        chooseDatePicker(initPair, ({
            viewModel.onChooseDate(it)
            binding.invalidateAll()
            viewModel.onDateClickedEventFinished()
        }), {
            viewModel.onDateClickedEventFinished()
        }, {
            viewModel.onDateClickedEventFinished()
        })
    }

    inner class ProjectSpinnerAdapter(context: Context, projectsList: List<Project>): BaseSpinnerAdapter<Project>(context, projectsList) {
        override fun onSetViews(item: Project, titleView: TextView, subTitleView: TextView, roundedLetter: TextView) {
            titleView.text = item.name
            subTitleView.text = item.customer
            roundedLetter.text = item.name[0].toString()
            roundedLetter.setBackgroundColorText(item.name)
        }
    }
}