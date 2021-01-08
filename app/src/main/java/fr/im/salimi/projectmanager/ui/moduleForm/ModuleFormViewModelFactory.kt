package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository

@Suppress("UNCHECKED_CAST")
class ModuleFormViewModelFactory(private val id: Long, private val repository: ModuleRepository, private val projectRepository: ProjectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModuleFormViewModel::class.java)) {
            return ModuleFormViewModel(id, repository, projectRepository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}