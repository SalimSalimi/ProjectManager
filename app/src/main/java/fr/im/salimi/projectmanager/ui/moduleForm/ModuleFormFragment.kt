package fr.im.salimi.projectmanager.ui.moduleForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.R

class ModuleFormFragment : Fragment() {

    companion object {
        fun newInstance() = ModuleFormFragment()
    }

    private lateinit var viewModel: ModuleFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.module_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ModuleFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}