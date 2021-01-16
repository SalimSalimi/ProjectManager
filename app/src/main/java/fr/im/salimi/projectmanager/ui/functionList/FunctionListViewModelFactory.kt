package fr.im.salimi.projectmanager.ui.functionList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository

@Suppress("UNCHECKED_CAST")
class FunctionListViewModelFactory(private val projectId: Long, private val repository: FunctionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FunctionListViewModel::class.java)) {
            return FunctionListViewModel(projectId, repository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}