package fr.im.salimi.projectmanager.ui.functionList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
class FunctionListViewModel(private val functionRepository: FunctionRepository) : ViewModel() {

    private val _functionsList = functionRepository.getAll()
    val functionsList: LiveData<List<Function>>
        get() = _functionsList.asLiveData()

}