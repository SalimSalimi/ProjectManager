package fr.im.salimi.projectmanager.ui.moduleForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.databinding.ModuleFormFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.chooseDatePicker

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
        binding = DataBindingUtil.inflate(inflater, R.layout.module_form_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.dateClickEvent.observe(viewLifecycleOwner) {
            if (it) {
                showDatePicker()
                viewModel.onDateClickedEventFinished()
            }
        }

        viewModel.addBtnClickEvent.observe(viewLifecycleOwner) {
            if (it)
                viewModel.onAddBtnClickedFinished()
        }
    }

    private fun showDatePicker() {
        val initPair = Pair(viewModel.module.value!!.startingDate.time,
                viewModel.module.value!!.endingDate.time)

        chooseDatePicker(initPair, ( {
            viewModel.onChooseDate(it)
            binding.invalidateAll()
        }), null, null)
    }
}