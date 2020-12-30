package fr.im.salimi.projectmanager.ui.functionForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository

@Suppress("UNCHECKED_CAST")
class FunctionFormViewModelFactory(private val id: Long, private val repository: FunctionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FunctionFormViewModel::class.java)) {
            return FunctionFormViewModel(id, repository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}