package fr.im.salimi.projectmanager.ui.projectForm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.im.salimi.projectmanager.data.entities.Project

class ProjectFormViewModel : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    init {
        _project.value = Project()
    }

    fun onAddBtnClicked() {
        Log.d("ProjectFormViewModel", "clicked")
        Log.d("ProjectFormViewModel", "clicked ${project.value.toString()}")
    }
}