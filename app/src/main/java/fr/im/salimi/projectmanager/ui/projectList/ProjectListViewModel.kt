package fr.im.salimi.projectmanager.ui.projectList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository

class ProjectListViewModel(repository: ProjectRepository) : ViewModel() {

    private val _projectsList = repository.getAll()
    val projectsList: LiveData<List<Project>>
        get() = _projectsList.asLiveData()

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