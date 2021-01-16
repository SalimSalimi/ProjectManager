package fr.im.salimi.projectmanager.ui.functionList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class FunctionListViewModel(private val projectId: Long, private val functionRepository: FunctionRepository) : ViewModel() {

    private val _functionsList: Flow<List<Function>> = flow {
        if (projectId == -1L)
            emitAll(functionRepository.getAll())
        else
            emitAll(functionRepository.geAllProjectById(projectId))
    }

    val functionsList: LiveData<List<Function>>
        get() = _functionsList.asLiveData()

    private val _navigateToFunctionFormEvent = MutableLiveData<Boolean>()
    val navigateToFunctionFormEvent: LiveData<Boolean>
        get() = _navigateToFunctionFormEvent

    init {
        _navigateToFunctionFormEvent.value = false
    }

    fun navigateToFunctionFormEventTriggered() {
        _navigateToFunctionFormEvent.value = true
    }

    fun navigateToFunctionFormEventDone() {
        _navigateToFunctionFormEvent.value = false
    }
}