package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository

@Suppress("UNCHECKED_CAST")
class ModuleFormViewModelFactory(private val id: Long, private val repository: ModuleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModuleFormViewModel::class.java)) {
            return ModuleFormViewModel(id, repository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}