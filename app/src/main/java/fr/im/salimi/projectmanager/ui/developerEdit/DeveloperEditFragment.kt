package fr.im.salimi.projectmanager.ui.editDeveloper

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.im.salimi.projectmanager.R

class EditDeveloperFragment : Fragment() {

    private lateinit var viewModel: EditDeveloperViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_developer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditDeveloperViewModel::class.java)
        // TODO: Use the ViewModel
    }

}