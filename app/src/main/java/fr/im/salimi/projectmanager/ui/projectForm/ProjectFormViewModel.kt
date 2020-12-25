package fr.im.salimi.projectmanager.ui.projectForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch
import java.util.*

class ProjectFormViewModel(private val projectRepository: ProjectRepository) : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    private val _dateClickedEvent = MutableLiveData<Boolean>()
    val dateClickedEvent: LiveData<Boolean>
        get() = _dateClickedEvent

    init {
        _project.value = Project()
        _dateClickedEvent.value = false
    }

    private fun insert() {
        viewModelScope.launch {
            projectRepository.insert(_project.value!!)
        }
    }

    fun onChooseDate(newStartingDate: Date, newEndingDate: Date) {
        _project.value?.apply {
            startingDate = newStartingDate
            deadline = newEndingDate
        }
    }

    fun onAddBtnClicked() {
        insert()
    }

    fun onDateClickedEvent() {
        _dateClickedEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickedEvent.value = false
    }
}