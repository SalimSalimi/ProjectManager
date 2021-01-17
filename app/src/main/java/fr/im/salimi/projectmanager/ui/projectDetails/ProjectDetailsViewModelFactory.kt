package fr.im.salimi.projectmanager.ui.projectDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ProjectDetailsViewModelFactory (private val projectId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectDetailsViewModel::class.java)) {
            return ProjectDetailsViewModel(projectId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}