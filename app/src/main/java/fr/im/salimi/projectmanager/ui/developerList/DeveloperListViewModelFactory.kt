package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository

@Suppress("UNCHECKED_CAST")
class DeveloperListViewModelFactory(private val projectId: Long, private val developerRepository: DeveloperRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeveloperListViewModel::class.java)) {
            return DeveloperListViewModel(projectId, developerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
