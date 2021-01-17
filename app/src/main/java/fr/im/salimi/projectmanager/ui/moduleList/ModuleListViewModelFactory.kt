package fr.im.salimi.projectmanager.ui.moduleList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ModuleListViewModelFactory(private val projectId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModuleListViewModel::class.java)) {
            return ModuleListViewModel(projectId) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}