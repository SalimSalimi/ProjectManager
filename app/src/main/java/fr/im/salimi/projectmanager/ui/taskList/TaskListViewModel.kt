package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.repositories.TaskRepository

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasksList = repository.getAll()
    val tasks: LiveData<List<Task>>
        get() = _tasksList.asLiveData()
}