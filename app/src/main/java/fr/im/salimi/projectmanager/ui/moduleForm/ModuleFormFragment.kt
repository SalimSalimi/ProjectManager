package fr.im.salimi.projectmanager.ui.moduleForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.databinding.ModuleFormFragmentBinding

class ModuleFormFragment : Fragment() {

    private lateinit var binding: ModuleFormFragmentBinding
    private val viewModel: ModuleFormViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val repository = ModuleRepository(database.moduleDao())
        ModuleFormViewModelFactory(-1L, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<ModuleFormFragmentBinding>(inflater, R.layout.module_form_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.dateClickEvent.observe(viewLifecycleOwner) {

            viewModel.onDateClickedEventFinished()
        }

        viewModel.addBtnClickEvent.observe(viewLifecycleOwner) {
            viewModel.onAddBtnClickedFinished()
        }
    }
}