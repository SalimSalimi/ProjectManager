package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ModuleFormViewModelFactory(private val id: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModuleFormViewModel::class.java)) {
            return ModuleFormViewModel(id) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}