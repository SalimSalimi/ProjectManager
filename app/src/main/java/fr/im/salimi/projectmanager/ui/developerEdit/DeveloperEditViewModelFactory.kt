package fr.im.salimi.projectmanager.ui.developerEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DeveloperEditViewModelFactory(private val id: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeveloperEditViewModel::class.java)) {
            return DeveloperEditViewModel(id) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}