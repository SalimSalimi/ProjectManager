package fr.im.salimi.projectmanager.ui.projectDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch

class ProjectDetailsViewModel(private val id: Long, private val projectRepository: ProjectRepository) : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    private val _getProjectIsDone = MutableLiveData<Boolean>()
    val getProjectIsDone: LiveData<Boolean>
        get() = _getProjectIsDone

    private val _modulesClickEvent = MutableLiveData<Boolean>()
    val modulesClickEvent: LiveData<Boolean>
        get() = _modulesClickEvent

    private val _functionsClickEvent = MutableLiveData<Boolean>()
    val functionsClickEvent: LiveData<Boolean>
        get() = _functionsClickEvent

    private val _tasksClickEvent = MutableLiveData<Boolean>()
    val tasksClickEvent: LiveData<Boolean>
        get() = _tasksClickEvent

    private val _developersClickEvent = MutableLiveData<Boolean>()
    val developersClickEvent: LiveData<Boolean>
        get() = _developersClickEvent


    init {
        _getProjectIsDone.value = false
        initProject()
    }
    
    fun onGetProjectDone() {
        _getProjectIsDone.value = false
    }
    
    fun onModulesClick() {
        _modulesClickEvent.value = true
    }

    fun onModulesClicked() {
        _modulesClickEvent.value = false
    }

    fun onFunctionsClick() {
        _functionsClickEvent.value = true
    }

    fun onFunctionsClicked() {
        _functionsClickEvent.value = false
    }

    fun onTasksClick() {
        _tasksClickEvent.value = true
    }

    fun onTasksClicked() {
        _tasksClickEvent.value = false
    }

    fun onDevelopersClick() {
        _developersClickEvent.value = true
    }

    fun onDevelopersClicked() {
        _developersClickEvent.value = false
    }


    private fun initProject() {
        viewModelScope.launch {
            _project.value = projectRepository.getById(id)
            _getProjectIsDone.value = true
        }
    }

}