package fr.im.salimi.projectmanager.ui.projectForm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import fr.im.salimi.projectmanager.databinding.ProjectFormFragmentBinding
import java.util.*

class ProjectFormFragment : Fragment() {

    private val viewModel: ProjectFormViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val dao = database.projectDao()
        ProjectFormViewModelFactory(-1L, ProjectRepository(dao))
    }

    private lateinit var binding: ProjectFormFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.project_form_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.dateClickedEvent.observe(viewLifecycleOwner) {
            if (it) {
                chooseDatePicker()
                viewModel.onDateClickedEventFinished()
            }
        }
    }

    private fun chooseDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(Pair(viewModel.project.value!!.startingDate.time, viewModel.project.value!!.deadline.time))
                .build()
        Log.d("ProjectFormFragment", "${viewModel.project.value!!.startingDate.time}")
        datePicker.show(parentFragmentManager, datePicker.toString())

        datePicker.addOnPositiveButtonClickListener {
            it?.let { pair ->
                val startingDate = Date(pair.first ?: Date().time)
                val endingDate = Date(pair.second ?: Date().time)
                Log.d("ProjectFormFragment", "${startingDate.time}")
                viewModel.onChooseDate(startingDate, endingDate)
                binding.invalidateAll()
            }
        }
    }
}