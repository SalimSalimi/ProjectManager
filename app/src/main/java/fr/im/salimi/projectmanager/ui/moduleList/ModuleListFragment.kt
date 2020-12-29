package fr.im.salimi.projectmanager.ui.moduleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.databinding.ModuleListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback

class ModuleListFragment : Fragment(), ClickListenersCallback<Module> {

    private lateinit var fabBtn: FloatingActionButton
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
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fabBtn = requireActivity().findViewById(R.id.fab_main)
        binding.viewModel = viewModel
        initAdapter()
        initObservers()
        fabBtn.apply {
            setOnClickListener {
                viewModel.onAddFabBtnClicked()
            }
            contentDescription = getString(R.string.create_a_new_module)
        }
    }

    private fun initObservers() {
        viewModel.modules.observe(viewLifecycleOwner) {
            moduleAdapter.submitList(it)
        }

        viewModel.navigateToModuleFormEvent.observe(viewLifecycleOwner) {
            if (it) {
                navigateToModuleForm(-1)
                viewModel.onNavigateToModuleFormEventTriggered()
            }
        }
    }

    private fun initAdapter() {
        binding.modulesList.apply {
            val layoutManager = LinearLayoutManager(requireContext())
            this.layoutManager = layoutManager
            moduleAdapter = ModuleListAdapter(this@ModuleListFragment)
            this.adapter = moduleAdapter
        }
    }

    private fun navigateToModuleForm(id: Long) {
        val directions =
                ModuleListFragmentDirections.actionModuleListFragmentToModuleFormFragment(id)
        this.findNavController().navigate(directions)
    }

    override fun onClick(view: View, entity: Module) {

    }

    override fun onLongClick(view: View, entity: Module): Boolean {
        navigateToModuleForm(entity.moduleId)
        return true
    }

}