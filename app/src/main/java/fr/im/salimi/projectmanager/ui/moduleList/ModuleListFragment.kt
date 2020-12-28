package fr.im.salimi.projectmanager.ui.moduleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.databinding.ModuleListFragmentBinding

class ModuleListFragment : Fragment() {

    private lateinit var moduleAdapter: ModuleListAdapter
    private lateinit var binding: ModuleListFragmentBinding
    private val viewModel: ModuleListViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val dao = database.moduleDao()
        ModuleListViewModelFactory(ModuleRepository(dao))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.module_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initAdapter()
        initObservers()
    }

    private fun initObservers() {
        viewModel.modules.observe(viewLifecycleOwner) {
            moduleAdapter.submitList(it)
        }

        viewModel.navigateToModuleFormEvent.observe(viewLifecycleOwner) {
            if (it)
                navigateToModuleForm()
        }
    }

    private fun initAdapter() {
        binding.modulesList.apply {
            val layoutManager = LinearLayoutManager(requireContext())
            this.layoutManager = layoutManager
            moduleAdapter = ModuleListAdapter()
            this.adapter = moduleAdapter
        }
    }

    private fun navigateToModuleForm() {

    }
}