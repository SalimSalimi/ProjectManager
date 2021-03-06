package fr.im.salimi.projectmanager.ui.moduleList

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
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.databinding.ModuleListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.createSnackbar
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour

class ModuleListFragment : Fragment(), ClickListenersCallback<Module> {

    private val args: ModuleListFragmentArgs by navArgs()
    private lateinit var moduleAdapter: ModuleListAdapter
    private lateinit var binding: ModuleListFragmentBinding
    private val viewModel: ModuleListViewModel by viewModels {
        ModuleListViewModelFactory(args.projectId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.module_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        setFabBtnBehaviour(FabButtonStates.PRIMARY_STATE) {
            viewModel.onAddFabBtnClicked()
        }
        initAdapter()
        initObservers()
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
        navigateToModuleForm(entity.id)
    }

    override fun onLongClick(view: View, entity: Module): Boolean {
        createSnackbar(text = R.string.delete_developer_confirm)
                .setAction(getString(R.string.confirm)) {
                    viewModel.deleteModule(entity)
                    moduleAdapter.notifyDataSetChanged()
                }
                .show()
        return true
    }

}