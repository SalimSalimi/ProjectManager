package fr.im.salimi.projectmanager.ui.featureList

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
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.databinding.FeatureListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.createSnackbar
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour

class FeatureListFragment : Fragment(), ClickListenersCallback<Feature> {

    private val args: FeatureListFragmentArgs by navArgs()
    private var sendingID = -1L
    private lateinit var myAdapter: FeatureListAdapter
    private lateinit var binding: FeatureListFragmentBinding
    private val viewModel: FeatureListViewModel by viewModels {
        FeatureListViewModelFactory(args.projectId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.feature_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        myAdapter = FeatureListAdapter(this)

        initObservers()
        initRecycler()
        fabBtnClick()
    }

    private fun initObservers() {
        viewModel.featuresList.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }

        viewModel.navigateToFeatureFormEvent.observe(viewLifecycleOwner) {
            if (it) {
                navigateToFeatureForm(sendingID)
                viewModel.navigateToFeatureFormEventDone()
            }
        }
    }

    private fun initRecycler() {
        binding.featuresList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }
    }

    private fun fabBtnClick() {
        setFabBtnBehaviour(FabButtonStates.PRIMARY_STATE) {
            sendingID = -1L
            viewModel.navigateToFeatureFormEventTriggered()
        }
    }

    private fun navigateToFeatureForm(id: Long) {
        val direction =
                FeatureListFragmentDirections.actionFeatureListFragmentToFeatureFormFragment(id)
        this.findNavController().navigate(direction)
    }

    override fun onClick(view: View, entity: Feature) {
        sendingID = entity.featureId
        viewModel.navigateToFeatureFormEventTriggered()
    }

    override fun onLongClick(view: View, entity: Feature): Boolean {
        createSnackbar(text = R.string.delete_developer_confirm)
                .setAction(getString(R.string.confirm)) {
                    viewModel.deleteFeature(entity)
                    myAdapter.notifyDataSetChanged()
                }
                .show()
        return true
    }
}