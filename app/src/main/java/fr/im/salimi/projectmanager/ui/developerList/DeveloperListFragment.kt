package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.im.salimi.projectmanager.R

class DeveloperListFragment : Fragment() {

    private lateinit var viewModel: DeveloperListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.developer_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeveloperListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}