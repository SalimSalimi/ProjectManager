package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(private val projectId: Long) : ViewModel() {

    private val _id = MutableLiveData<Long>()

    val tasksList: LiveData<List<Task>> = Transformations.map(_id) {
        if (it != -1L)
            TaskRepository.getAllByProjectId(it).value
        else
            TaskRepository.getAll().value
    }

    private val _navigateToTaskFormEvent = MutableLiveData<Boolean>()
    val navigateToTaskFormEvent: LiveData<Boolean>
        get () = _navigateToTaskFormEvent

    init {
        _id.value = projectId
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