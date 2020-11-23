package fr.im.salimi.projectmanager.ui.developerEdit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.helpers.Post
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.databinding.DeveloperEditFragmentBinding

class DeveloperEditFragment : Fragment() {

    private lateinit var binding: DeveloperEditFragmentBinding
    private lateinit var viewModel: DeveloperEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.developer_edit_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        val databaseInstance = ProjectRoomDatabase.getInstance(requireContext())
        val repository = DeveloperRepository(databaseInstance.developerDao())
        val viewModel: DeveloperEditViewModel by viewModels {
            DeveloperEditViewModelFactory(repository)
        }
        val adapter: ArrayAdapter<Post> = ArrayAdapter(requireContext(), R.layout.posts_dropdown_menu_layout, Post.values())
        binding.viewModel = viewModel
        binding.spinnerPost.setAdapter(adapter)
    }
}