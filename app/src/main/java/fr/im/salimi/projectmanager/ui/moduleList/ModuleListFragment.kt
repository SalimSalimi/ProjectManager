package fr.im.salimi.projectmanager.ui.moduleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.R

class ModuleListFragment : Fragment() {

    companion object {
        fun newInstance() = ModuleListFragment()
    }

    private lateinit var viewModel: ModuleListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.module_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ModuleListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}