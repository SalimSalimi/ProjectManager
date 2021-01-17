package fr.im.salimi.projectmanager.ui.featureForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.databinding.FeatureFormFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseSpinnerAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.chooseDatePicker
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour
import java.util.*

class FeatureFormFragment : Fragment() {

    private val args: FeatureFormFragmentArgs by navArgs()
    private lateinit var binding: FeatureFormFragmentBinding
    private val viewModel: FeatureFormViewModel by viewModels {
        FeatureFormViewModelFactory(args.featureId)
    }

    private lateinit var spinnerAdapter: ModuleSpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.feature_form_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initObservers()

        spinnerAdapter = ModuleSpinnerAdapter(listOf())
        binding.editTextFeatureModule.setAdapter(spinnerAdapter)
        binding.editTextFeatureModule.onItemClickListener = itemClickListener

        setFabBtnBehaviour(FabButtonStates.SECONDARY_STATE) {
            fabBtnClicked()
        }
    }

    private fun initObservers() {
        viewModel.dateClickEvent.observe(viewLifecycleOwner) {
            if (it)
                onDateChooser()
        }

        viewModel.addFabBtnClickEvent.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.upsert()
                viewModel.onAddFabClickedEventFinished()
                this.findNavController().navigate(R.id.action_featureFormFragment_to_featureListFragment)
            }
        }

        viewModel.modulesList.observe(viewLifecycleOwner) {
            spinnerAdapter.setEntitiesList(it)
            if (args.featureId != -1L) {
                 viewModel.onSetModule()
            }
        }

        viewModel.module.observe(viewLifecycleOwner) {
            setDefaultSpinnerSelection(it)
        }
    }

    private fun onDateChooser() {
        val initValues = Pair<Long, Long>(
            viewModel.feature.value?.startingDate?.time,
            viewModel.feature.value?.endingDate?.time
        )
        chooseDatePicker(initValues, {
            val startingDate = Date(it.first ?: Date().time)
            val endingDate = Date(it.second ?: Date().time)
            viewModel.onChooseDate(startingDate, endingDate)
            viewModel.onDateClickedEventFinished()
            binding.invalidateAll()
        }, {
            viewModel.onDateClickedEventFinished()
        }, {
            viewModel.onDateClickedEventFinished()
        })
    }

    private val itemClickListener: AdapterView.OnItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
        val module = spinnerAdapter.getItem(position)
        viewModel.setModuleId(module!!.id)
        viewModel.setProjectId(module.projectId)
    }

    private fun fabBtnClicked() {
        viewModel.onAddFabBtnClickEvent()
    }

    private fun setDefaultSpinnerSelection(module: Module) {
        binding.editTextFeatureModule.setText(module.toString(), false)
    }

    inner class ModuleSpinnerAdapter(modulesList: List<Module>): BaseSpinnerAdapter<Module>(requireContext(), modulesList) {
        override fun onSetViews(item: Module, titleView: TextView, subTitleView: TextView, roundedLetter: TextView) {
            titleView.text = item.name
            subTitleView.text = item.description
            roundedLetter.visibility = View.GONE
        }
    }
}