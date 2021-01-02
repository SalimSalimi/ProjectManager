package fr.im.salimi.projectmanager.ui.taskForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import fr.im.salimi.projectmanager.databinding.TaskFormFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.chooseDatePicker
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour
import java.util.*

class TaskFormFragment : Fragment() {

    val args: TaskFormFragmentArgs by navArgs()
    private lateinit var binding: TaskFormFragmentBinding
    private val viewModel: TaskFormViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val dao = database.taskDao()
        TaskFormViewModelFactory(args.taskId, TaskRepository(dao))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.task_form_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
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

    private fun fabBtnClicked() {
        viewModel.onAddFabBtnClickEvent()
    }

}