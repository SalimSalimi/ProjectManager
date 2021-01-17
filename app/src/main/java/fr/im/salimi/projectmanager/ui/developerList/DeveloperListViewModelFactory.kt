package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DeveloperListViewModelFactory(private val projectId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeveloperListViewModel::class.java)) {
            return DeveloperListViewModel(projectId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
