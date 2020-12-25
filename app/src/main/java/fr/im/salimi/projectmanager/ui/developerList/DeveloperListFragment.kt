package fr.im.salimi.projectmanager.ui.developerList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.databinding.DeveloperListFragmentBinding

class DeveloperListFragment : Fragment(), DeveloperAdapterListener {

    private var developersList: List<Developer> = ArrayList()
    private lateinit var binding: DeveloperListFragmentBinding
    private lateinit var myAdapter: DeveloperListAdapter

    private val viewModel: DeveloperListViewModel by viewModels {
        val database = ProjectRoomDatabase.getInstance(requireContext())
        val repository = DeveloperRepository(database.developerDao())
        DeveloperListViewModelFactory(repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.developer_list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.fab.setOnClickListener {
            this.findNavController()
                    .navigate(R.id.action_developerListFragment_to_editDeveloperFragment)
        }
        initObservers()
        initRecycler()
        return binding.root
    }

    private fun initRecycler() {
        binding.developersList.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            myAdapter = DeveloperListAdapter(developersList, this@DeveloperListFragment)
            this.layoutManager = linearLayoutManager
            this.adapter = myAdapter
        }
    }

    private fun initObservers() {
        viewModel.listDevelopers.observe(viewLifecycleOwner) {
            myAdapter.developersList = it
            myAdapter.notifyDataSetChanged()
        }
    }

    override fun onLongClick(cardView: View, developer: Developer): Boolean {
        val directions =
                DeveloperListFragmentDirections.actionDeveloperListFragmentToEditDeveloperFragment(developer.developerId)
        this.findNavController().navigate(directions)
        return true
    }

    override fun onClick(cardView: View, developer: Developer) {
        Snackbar.make(requireView(), getString(R.string.delete_developer_confirm), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.confirm)) {
                    viewModel.deleteDeveloper(developer)
                    myAdapter.notifyDataSetChanged()
                }
                .show()
    }
}