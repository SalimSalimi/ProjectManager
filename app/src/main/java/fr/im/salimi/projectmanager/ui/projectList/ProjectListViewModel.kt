package fr.im.salimi.projectmanager.ui.projectList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository

class ProjectListViewModel : ViewModel() {

    val projectsList: LiveData<List<Project>>
        get() = ProjectRepository.getAll()

    private val _navigateToProjectForm = MutableLiveData<Boolean>()
    val navigateToProjectForm: LiveData<Boolean>
        get() = _navigateToProjectForm

    init {
        _navigateToProjectForm.value = false
    }

    fun onAddBtnClicked() {
        navigateToProjectForm()
    }

    private fun navigateToProjectForm() {
        _navigateToProjectForm.value = true
    }

    fun doneNavigatingToProjectForm() {
        _navigateToProjectForm.value = false
    }
}