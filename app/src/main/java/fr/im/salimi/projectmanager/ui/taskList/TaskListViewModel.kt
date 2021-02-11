package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(private val projectId: Long) : ViewModel() {

    private val _tasksList = MutableLiveData<List<Task>>()
    val tasksList: LiveData<List<Task>>
        get() = _tasksList

    private val _navigateToTaskFormEvent = MutableLiveData<Boolean>()
    val navigateToTaskFormEvent: LiveData<Boolean>
        get () = _navigateToTaskFormEvent

    init {
        initTaskList()
        _navigateToTaskFormEvent.value = false
    }

    fun navigateToTaskFormEventTriggered() {
        _navigateToTaskFormEvent.value = true
    }

    fun navigateToTaskFormEventDone() {
        _navigateToTaskFormEvent.value = false
    }

    fun deleteFeature(task: Task) {
        viewModelScope.launch {
            TaskRepository.delete(task)
        }
    }

    private fun initTaskList() {
        if (projectId != -1L)
            _tasksList.value = TaskRepository.getAllByProjectId(projectId).value
        else
            _tasksList.value = TaskRepository.getAll().value
    }
}