package fr.im.salimi.projectmanager.ui.projectDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import fr.im.salimi.projectmanager.databinding.ProjectDetailsFragmentBinding
import fr.im.salimi.projectmanager.ui.uiUtils.setBackgroundColorText

class ProjectDetailsFragment : Fragment() {

    private lateinit var binding: ProjectDetailsFragmentBinding
    private val viewModel: ProjectDetailsViewModel by viewModels {
        val projectDao = ProjectRoomDatabase.getInstance(requireContext()).projectDao()
        val repository = ProjectRepository(projectDao)
        ProjectDetailsViewModelFactory(1L, repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater ,R.layout.project_details_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        viewModel.getProjectIsDone.observe(viewLifecycleOwner) {
            if (it) {
                binding.roundedLetter.setBackgroundColorText(viewModel.project.value!!.name)
                viewModel.onGetProjectDone()
            }
        }

        return binding.root
    }


}