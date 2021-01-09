package fr.im.salimi.projectmanager.ui.taskForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.data.repositories.TaskRepository

@Suppress("UNCHECKED_CAST")
class TaskFormViewModelFactory(private val taskId: Long, private val taskRepository: TaskRepository,
                               private val developerRepository: DeveloperRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskFormViewModel::class.java)) {
            return TaskFormViewModel(taskId, taskRepository, developerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}