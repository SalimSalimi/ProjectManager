package fr.im.salimi.projectmanager.ui.projectList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.databinding.ProjectListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour

class ProjectListFragment : Fragment(), ClickListenersCallback<Project> {

    private lateinit var binding: ProjectListFragmentBinding
    private lateinit var myAdapter: ProjectListAdapter

    private val viewModel: ProjectListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.project_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        setFabBtnBehaviour(FabButtonStates.PRIMARY_STATE) {
            viewModel.onAddBtnClicked()
        }

        initObservers()
        initAdapter()
    }

    private fun initObservers() {
        //Observing list data
        viewModel.projectsList.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }

        //Observe navigation
        viewModel.navigateToProjectForm.observe(viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(R.id.action_projectListFragment_to_projectFormFragment)
                viewModel.doneNavigatingToProjectForm()
            }
        }
    }

    private fun initAdapter() {
        binding.projectsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            myAdapter = ProjectListAdapter( this@ProjectListFragment)
            this.adapter = myAdapter
        }
    }

    override fun onClick(view: View, entity: Project) {
        //Navigate to ProjectDetails
        val directions =
                ProjectListFragmentDirections.actionProjectListFragmentToProjectDetailsFragment(entity.id)
        this.findNavController().navigate(directions)
    }

    override fun onLongClick(view: View, entity: Project): Boolean {
        val directions =
                ProjectListFragmentDirections.actionProjectListFragmentToProjectFormFragment(entity.id)
        this.findNavController().navigate(directions)
        return true
    }
}