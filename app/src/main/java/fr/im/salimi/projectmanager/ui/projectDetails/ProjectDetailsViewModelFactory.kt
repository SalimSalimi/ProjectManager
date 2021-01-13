package fr.im.salimi.projectmanager.ui.projectDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository

@Suppress("UNCHECKED_CAST")
class ProjectDetailsViewModelFactory (private val projectId: Long, private val projectRepository: ProjectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectDetailsViewModel::class.java)) {
            return ProjectDetailsViewModel(projectId, projectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}