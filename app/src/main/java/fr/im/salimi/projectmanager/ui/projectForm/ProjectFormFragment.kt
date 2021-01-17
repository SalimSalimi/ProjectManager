package fr.im.salimi.projectmanager.ui.projectForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.databinding.ProjectFormFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.chooseDatePicker
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour
import java.util.*

class ProjectFormFragment : Fragment() {

    private val args: ProjectFormFragmentArgs by navArgs()
    private val viewModel: ProjectFormViewModel by viewModels {
        ProjectFormViewModelFactory(args.projectId)
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
        setFabBtnBehaviour(FabButtonStates.SECONDARY_STATE) {
            viewModel.onAddBtnClicked()
        }

        initObservers()
    }

    private fun initObservers() {
        //When the user clicks to choose a date
        viewModel.dateClickedEvent.observe(viewLifecycleOwner) {
            if (it) {
                chooseDatePicker()
            }
        }

        //Navigation to ProjectListFragment
        viewModel.navigateToProjectList.observe(viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(R.id.action_projectFormFragment_to_projectListFragment)
                viewModel.doneNavigateToProjectList()
            }
        }
    }

    private fun chooseDatePicker() {
        val initDates =
                Pair(viewModel.project.value!!.startingDate.time, viewModel.project.value!!.deadline.time)

        chooseDatePicker(initDates, {
            it.let { pair ->
                val startingDate = Date(pair.first ?: Date().time)
                val endingDate = Date(pair.second ?: Date().time)
                viewModel.onChooseDate(startingDate, endingDate)
                binding.invalidateAll()
                viewModel.onDateClickedEventFinished()
            }
        }, {
            viewModel.onDateClickedEventFinished()
        }, {
            viewModel.onDateClickedEventFinished()
        })
    }
}