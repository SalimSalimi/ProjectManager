package fr.im.salimi.projectmanager.ui.projectForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ProjectFormViewModelFactory (private val projectId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectFormViewModel::class.java)) {
            return ProjectFormViewModel(projectId) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}