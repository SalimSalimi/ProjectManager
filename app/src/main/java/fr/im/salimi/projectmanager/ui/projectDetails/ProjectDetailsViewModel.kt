package fr.im.salimi.projectmanager.ui.projectDetails

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.subsets.ProjectState
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class ProjectDetailsViewModel(private val id: Long) : ViewModel() {

    private val _projectState: Flow<ProjectState> = flow {
        if (id != -1L)
            emitAll(ProjectRepository.getProjectStateById(id))
    }
    val projectState: LiveData<ProjectState> = Transformations.map(_projectState.asLiveData()) {
        it
    }

    private val _getProjectIsDone = MutableLiveData<Boolean>()
    val getProjectIsDone: LiveData<Boolean>
        get() = _getProjectIsDone

    private val _modulesClickEvent = MutableLiveData<Boolean>()
    val modulesClickEvent: LiveData<Boolean>
        get() = _modulesClickEvent

    private val _featuresClickEvent = MutableLiveData<Boolean>()
    val featuresClickEvent: LiveData<Boolean>
        get() = _featuresClickEvent

    private val _tasksClickEvent = MutableLiveData<Boolean>()
    val tasksClickEvent: LiveData<Boolean>
        get() = _tasksClickEvent

    private val _developersClickEvent = MutableLiveData<Boolean>()
    val developersClickEvent: LiveData<Boolean>
        get() = _developersClickEvent


    init {
        initEvents()
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

    fun onFeaturesClick() {
        _featuresClickEvent.value = true
    }

    fun onFeaturesClicked() {
        _featuresClickEvent.value = false
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

    private fun initEvents() {
        _getProjectIsDone.value = false
        _modulesClickEvent.value = false
        _featuresClickEvent.value = false
        _tasksClickEvent.value = false
        _developersClickEvent.value = false
    }
}