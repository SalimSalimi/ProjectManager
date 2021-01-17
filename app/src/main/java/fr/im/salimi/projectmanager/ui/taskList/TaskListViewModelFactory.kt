package fr.im.salimi.projectmanager.ui.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TaskListViewModelFactory(private val projectId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(projectId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}