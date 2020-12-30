package fr.im.salimi.projectmanager.ui.functionList

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
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
import fr.im.salimi.projectmanager.databinding.FunctionListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour

class FunctionListFragment : Fragment(), ClickListenersCallback<Function> {

    private lateinit var myAdapter: FunctionListAdapter
    private lateinit var binding: FunctionListFragmentBinding
    private val viewModel: FunctionListViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val repository = FunctionRepository(database.functionDao())
        FunctionListViewModelFactory(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.function_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        myAdapter = FunctionListAdapter(this)

        initObservers()
        initRecycler()
        fabBtnClick()
    }

    private fun initObservers() {
        viewModel.functionsList.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }
    }

    private fun initRecycler() {
        binding.functionsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }
    }

    private fun fabBtnClick() {
        setFabBtnBehaviour(FabButtonStates.PRIMARY_STATE) {

        }
    }

    override fun onClick(view: View, entity: Function) {
        //Add something here
    }

    override fun onLongClick(view: View, entity: Function): Boolean {
        //Add Navigation
        return true
    }
}