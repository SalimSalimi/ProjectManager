package fr.im.salimi.projectmanager.ui.projectForm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.im.salimi.projectmanager.R

class ProjectFormFragment : Fragment() {

    companion object {
        fun newInstance() = ProjectFormFragment()
    }

    private lateinit var viewModel: ProjectFormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.project_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectFormViewModel::class.java)

    }

}