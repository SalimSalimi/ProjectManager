package fr.im.salimi.projectmanager.ui.moduleList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository

@Suppress("UNCHECKED_CAST")
class ModuleListViewModelFactory(private val projectId: Long, private val repository: ModuleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModuleListViewModel::class.java)) {
            return ModuleListViewModel(projectId, repository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}