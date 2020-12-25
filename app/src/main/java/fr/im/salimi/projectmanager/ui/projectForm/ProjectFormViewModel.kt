package fr.im.salimi.projectmanager.ui.projectForm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch

class ProjectFormViewModel(private val projectRepository: ProjectRepository) : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    init {
        _project.value = Project()
    }

    fun onAddBtnClicked() {
        insert()
    }

    private fun insert() {
        viewModelScope.launch {
            projectRepository.insert(_project.value!!)
        }
    }
}