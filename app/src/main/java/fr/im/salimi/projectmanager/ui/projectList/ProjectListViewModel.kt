package fr.im.salimi.projectmanager.ui.projectList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.subsets.ProjectState
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository

class ProjectListViewModel(repository: ProjectRepository) : ViewModel() {

    private val _projectsList = repository.getAllProjectState()
    val projectsList: LiveData<List<ProjectState>>
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