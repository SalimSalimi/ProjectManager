package fr.im.salimi.projectmanager.ui.taskList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.databinding.TaskListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.createSnackbar
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour

class TaskListFragment : Fragment(), ClickListenersCallback<Task> {

    private val args: TaskListFragmentArgs by navArgs()
    private var sendingID: Long = -1L
    private lateinit var myAdapter: TaskListAdapter
    private lateinit var binding: TaskListFragmentBinding
    private val viewModel: TaskListViewModel by viewModels {
        TaskListViewModelFactory(sendingID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.task_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sendingID = args.projectId
        binding.viewModel = viewModel

        initRecycler()
        initObservers()
        fabBtnClick()
    }

    private fun initObservers() {
        viewModel.tasksList.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }

        viewModel.navigateToTaskFormEvent.observe(viewLifecycleOwner) {
            if (it) {
                navigateToTaskForm(sendingID)
                viewModel.navigateToTaskFormEventDone()
            }
        }
    }

    private fun initRecycler() {
        myAdapter = TaskListAdapter(this)
        binding.tasksList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }
    }

    private fun fabBtnClick() {
        setFabBtnBehaviour(FabButtonStates.PRIMARY_STATE) {
            sendingID = -1L
            viewModel.navigateToTaskFormEventTriggered()
        }
    }

    private fun navigateToTaskForm(id: Long) {
        val direction =
            TaskListFragmentDirections.actionTaskListFragmentToTaskFormFragment(id)
        this.findNavController().navigate(direction)
    }

    override fun onClick(view: View, entity: Task) {
        navigateToTaskForm(entity.taskId)
    }

    override fun onLongClick(view: View, entity: Task): Boolean {
        createSnackbar(text = R.string.delete_developer_confirm)
                .setAction(getString(R.string.confirm)) {
                    viewModel.deleteFeature(entity)
                    myAdapter.notifyDataSetChanged()
                }
                .show()
        return true
    }

}