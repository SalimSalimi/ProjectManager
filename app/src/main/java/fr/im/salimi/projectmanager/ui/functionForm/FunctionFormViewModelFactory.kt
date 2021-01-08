package fr.im.salimi.projectmanager.ui.functionForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository

@Suppress("UNCHECKED_CAST")
class FunctionFormViewModelFactory(private val id: Long, private val repository: FunctionRepository,
                                   private val moduleRepository: ModuleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FunctionFormViewModel::class.java)) {
            return FunctionFormViewModel(id, repository, moduleRepository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}