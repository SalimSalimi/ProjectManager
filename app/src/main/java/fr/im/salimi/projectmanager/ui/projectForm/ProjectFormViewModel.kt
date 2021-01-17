package fr.im.salimi.projectmanager.ui.projectForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch
import java.util.*

class ProjectFormViewModel(private val projectId: Long) : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    private val _dateClickedEvent = MutableLiveData<Boolean>()
    val dateClickedEvent: LiveData<Boolean>
        get() = _dateClickedEvent

    private val _navigateToProjectList = MutableLiveData<Boolean>()
    val navigateToProjectList: LiveData<Boolean>
        get() = _navigateToProjectList

    init {
        initProject()
        _navigateToProjectList.value = false
        _dateClickedEvent.value = false
    }

    private fun initProject() {
        if (projectId == -1L)
            _project.value = Project()
        else
            viewModelScope.launch {
                _project.value = ProjectRepository.getById(projectId)
            }
    }

    private fun insert() {
        viewModelScope.launch {
            ProjectRepository.insert(_project.value!!)
        }
    }

    private fun update() {
        viewModelScope.launch {
            ProjectRepository.update(_project.value!!)
        }
    }

    private fun upsert() {
        if (projectId == -1L)
            insert()
        else
            update()
    }

    fun onChooseDate(newStartingDate: Date, newEndingDate: Date) {
        _project.value?.apply {
            startingDate = newStartingDate
            deadline = newEndingDate
        }
    }

    fun onAddBtnClicked() {
        upsert()
        navigateToProjectList()
    }

    fun onDateClickedEvent() {
        _dateClickedEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickedEvent.value = false
    }

    private fun navigateToProjectList() {
        _navigateToProjectList.value = true
    }

    fun doneNavigateToProjectList() {
        _navigateToProjectList.value = false
    }
}