package fr.im.salimi.projectmanager.ui.projectForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch
import java.util.*

class ProjectFormViewModel(private val projectId: Long, private val projectRepository: ProjectRepository) : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    private val _dateClickedEvent = MutableLiveData<Boolean>()
    val dateClickedEvent: LiveData<Boolean>
        get() = _dateClickedEvent

    init {
        initProject()
        _dateClickedEvent.value = false
    }

    private fun initProject() {
        if (projectId == -1L)
            _project.value = Project()
        else
            viewModelScope.launch {
                _project.value = projectRepository.getById(projectId)
            }
    }

    private fun insert() {
        viewModelScope.launch {
            projectRepository.insert(_project.value!!)
        }
    }

    private fun update() {
        viewModelScope.launch {
            projectRepository.update(_project.value!!)
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
    }

    fun onDateClickedEvent() {
        _dateClickedEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickedEvent.value = false
    }
}