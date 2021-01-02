package fr.im.salimi.projectmanager.ui.taskForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.R

class TaskFormFragment : Fragment() {

    companion object {
        fun newInstance() = TaskFormFragment()
    }

    private lateinit var viewModel: TaskFormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.task_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}