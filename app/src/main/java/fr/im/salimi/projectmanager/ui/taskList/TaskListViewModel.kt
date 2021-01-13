package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasksList: Flow<List<Task>> = flow {
        emitAll(repository.getAllByProjectId(1L))
    }

    val tasksList: LiveData<List<Task>>
        get() = _tasksList.asLiveData()

    private val _navigateToTaskFormEvent = MutableLiveData<Boolean>()
    val navigateToTaskFormEvent: LiveData<Boolean>
        get () = _navigateToTaskFormEvent

    init {
        _navigateToTaskFormEvent.value = false
    }

    fun navigateToTaskFormEventTriggered() {
        _navigateToTaskFormEvent.value = true
    }

    fun navigateToTaskFormEventDone() {
        _navigateToTaskFormEvent.value = false
    }
}