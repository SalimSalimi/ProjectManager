package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TaskListViewModel(private val projectId: Long) : ViewModel() {

    private val _tasksList: Flow<List<Task>> = flow {
        if (projectId != -1)
            emitAll(TaskRepository.getAllByProjectId(projectId))
        else
            emitAll(TaskRepository.getAll())
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

    fun deleteFeature(task: Task) {
        viewModelScope.launch {
            TaskRepository.delete(task)
        }
    }
}