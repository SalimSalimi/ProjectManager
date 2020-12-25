package fr.im.salimi.projectmanager.ui.projectList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProjectListViewModelFactory (private val projectRepository: ProjectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectListViewModel::class.java)) {
            return ProjectListViewModel(projectRepository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}