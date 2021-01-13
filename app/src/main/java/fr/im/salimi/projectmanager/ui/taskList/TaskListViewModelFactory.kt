package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.TaskRepository

@Suppress("UNCHECKED_CAST")
class TaskListViewModelFactory(private val projectId: Long, private val taskRepository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(projectId, taskRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}