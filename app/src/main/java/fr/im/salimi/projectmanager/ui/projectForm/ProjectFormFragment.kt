package fr.im.salimi.projectmanager.ui.projectForm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.databinding.ProjectFormFragmentBinding

class ProjectFormFragment : Fragment() {

    private lateinit var viewModel: ProjectFormViewModel
    private lateinit var binding: ProjectFormFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.project_form_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectFormViewModel::class.java)
        binding.viewModel = viewModel
    }

}