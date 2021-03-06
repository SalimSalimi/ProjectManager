package fr.im.salimi.projectmanager.ui.developerList

import android.os.Bundle
import android.util.Log
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
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.helpers.Post
import fr.im.salimi.projectmanager.databinding.DeveloperListFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.*

class DeveloperListFragment : Fragment(), ClickListenersCallback<Developer> {

    private val args: DeveloperListFragmentArgs by navArgs()
    private lateinit var binding: DeveloperListFragmentBinding
    private lateinit var myAdapter: DeveloperListAdapter

    private val viewModel: DeveloperListViewModel by viewModels {
        DeveloperListViewModelFactory(args.projectId)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.developer_list_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setFabBtnBehaviour(FabButtonStates.PRIMARY_STATE) {
            viewModel.onAddBtnClickedEvent()
        }
        initObservers()
        initRecycler()
        filters()
    }
    private fun initRecycler() {
        binding.developersList.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            myAdapter = DeveloperListAdapter( this@DeveloperListFragment)
            this.layoutManager = linearLayoutManager
            this.adapter = myAdapter
        }
    }

    private fun initObservers() {
        viewModel.listDevelopers.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
            Log.d("DeveloperListFragment", "yaaaw")
        }

        viewModel.onAddBtnClickEvent.observe(viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(R.id.action_developerListFragment_to_editDeveloperFragment)
                viewModel.onAddBtnClickedEventDone()
            }
        }
    }

    override fun onLongClick(view: View, entity: Developer): Boolean {
        val directions =
                DeveloperListFragmentDirections.actionDeveloperListFragmentToEditDeveloperFragment(entity.id)
        this.findNavController().navigate(directions)
        return true
    }

    override fun onClick(view: View, entity: Developer) {
        createSnackbar(text = R.string.delete_developer_confirm)
                .setAction(getString(R.string.confirm)) {
                    viewModel.deleteDeveloper(entity)
                    myAdapter.notifyDataSetChanged()
                }
                .show()
    }

   private fun filters() {
        binding.filterGroup.setOnCheckedChangeListener { _, checkedIp ->
            if (checkedIp == -1)
                return@setOnCheckedChangeListener
            when(checkedIp) {
                binding.filterAll.id -> viewModel.setFilter(Post.NONE)
                binding.filterMobile.id -> viewModel.setFilter(Post.MOBILE_DEVELOPER)
                binding.filterWeb.id -> viewModel.setFilter(Post.WEB_DEVELOPER)
                binding.filterUiUx.id -> viewModel.setFilter(Post.UI_UX_DESIGNER)
            }
        }
    }
}