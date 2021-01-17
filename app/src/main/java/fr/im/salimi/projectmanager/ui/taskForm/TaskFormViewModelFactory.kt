package fr.im.salimi.projectmanager.ui.taskForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TaskFormViewModelFactory(private val taskId: Long)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskFormViewModel::class.java)) {
            return TaskFormViewModel(taskId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}