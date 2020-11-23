package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DeveloperListViewModelFactory(private val developerRepository: DeveloperRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeveloperListViewModel::class.java)) {
            return DeveloperListViewModel(developerRepository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}
