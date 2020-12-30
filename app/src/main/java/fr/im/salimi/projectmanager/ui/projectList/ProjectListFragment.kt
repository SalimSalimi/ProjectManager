package fr.im.salimi.projectmanager.ui.projectList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import fr.im.salimi.projectmanager.databinding.ProjectListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.changeFabState

class ProjectListFragment : Fragment(), ClickListenersCallback<Project> {

    private lateinit var fabBtn: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var binding: ProjectListFragmentBinding
    private lateinit var myAdapter: ProjectListAdapter

    private val viewModel: ProjectListViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val repository = ProjectRepository(database.projectDao())
        ProjectListViewModelFactory(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.project_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fabBtn = requireActivity().findViewById(R.id.fab_main)
        bottomAppBar = requireActivity().findViewById(R.id.bottom_app_bar)
        fabBtn.apply {
            changeFabState(FabButtonStates.PRIMARY_STATE, bottomAppBar)
            setOnClickListener {
                viewModel.onAddBtnClicked()
            }
            contentDescription = getString(R.string.create_a_new_project)
        }

        binding.viewModel = viewModel

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
            layoutManager = GridLayoutManager(requireContext(), 2)
            myAdapter = ProjectListAdapter( this@ProjectListFragment)
            this.adapter = myAdapter
        }
    }

    override fun onClick(view: View, entity: Project) {

    }

    override fun onLongClick(view: View, entity: Project): Boolean {
        val directions =
                ProjectListFragmentDirections.actionProjectListFragmentToProjectFormFragment(entity.projectId)
        this.findNavController().navigate(directions)
        return true
    }
}