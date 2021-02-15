package fr.im.salimi.projectmanager.ui.taskForm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TaskFormViewModelFactory(private val application: Application, private val taskId: Long)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskFormViewModel::class.java)) {
            return TaskFormViewModel(application, taskId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}