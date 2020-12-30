package fr.im.salimi.projectmanager.ui.developerEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.helpers.Post
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.databinding.DeveloperEditFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.FabButtonStates
import fr.im.salimi.projectmanager.ui.uiUtils.setFabBtnBehaviour

class DeveloperEditFragment : Fragment() {

    private lateinit var fabBtn: FloatingActionButton
    private lateinit var bottomBar: BottomAppBar

    private lateinit var binding: DeveloperEditFragmentBinding
    private val args: DeveloperEditFragmentArgs by navArgs()
    private val viewModel: DeveloperEditViewModel by viewModels {
        val databaseInstance = ProjectRoomDatabase.getInstance(requireContext())
        val repository = DeveloperRepository(databaseInstance.developerDao())
        DeveloperEditViewModelFactory(args.developerId, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.developer_edit_fragment, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setFabBtnBehaviour(FabButtonStates.SECONDARY_STATE) {
            viewModel.onAddClick()
        }
    }

    private fun init() {
        binding.viewModel = viewModel
        val adapter: ArrayAdapter<Post> = ArrayAdapter(requireContext(), R.layout.posts_dropdown_menu_layout, Post.values())
        binding.spinnerPost.setAdapter(adapter)

        //Init observers
        viewModel.showSnackbarConfirm.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(requireView(), getString(R.string.snackbar_text_add_success), Snackbar.LENGTH_LONG).show()
                viewModel.showSnackbarDone()
            }
        }

        viewModel.navigateToList.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_editDeveloperFragment_to_developerListFragment)
                viewModel.navigateToListDone()
            }
        }
    }
}