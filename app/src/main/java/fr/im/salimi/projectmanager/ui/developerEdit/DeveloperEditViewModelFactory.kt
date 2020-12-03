package fr.im.salimi.projectmanager.ui.developerEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DeveloperEditViewModelFactory(private val id: Long,private val developerRepository: DeveloperRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeveloperEditViewModel::class.java)) {
            return DeveloperEditViewModel(id, developerRepository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}