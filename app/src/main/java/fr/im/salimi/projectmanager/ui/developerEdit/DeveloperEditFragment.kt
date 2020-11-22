package fr.im.salimi.projectmanager.ui.developerEdit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fr.im.salimi.projectmanager.R
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
        viewModel = ViewModelProvider(this).get(DeveloperEditViewModel::class.java)
        binding.viewModel = viewModel
    }

}