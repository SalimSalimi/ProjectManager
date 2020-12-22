package fr.im.salimi.projectmanager.ui.projectList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.databinding.ProjectListFragmentBinding

class ProjectListFragment : Fragment() {

    private lateinit var viewModel: ProjectListViewModel
    private lateinit var projectsList: List<Project>
    private lateinit var binding: ProjectListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.project_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectListViewModel::class.java)

    }

    private fun initObservers() {
        viewModel.projectsList.observe(viewLifecycleOwner) {
            projectsList = it
        }
    }

    private fun initAdapter() {
        binding.projectsList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            val myAdapter = ProjectListAdapter(projectsList)
            this.adapter = myAdapter
        }
    }
}