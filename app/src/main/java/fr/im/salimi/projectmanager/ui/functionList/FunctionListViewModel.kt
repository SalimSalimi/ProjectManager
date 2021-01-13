package fr.im.salimi.projectmanager.ui.functionList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
class FunctionListViewModel(private val functionRepository: FunctionRepository) : ViewModel() {

    private val _functionsList = functionRepository.getAll()
    val functionsList: LiveData<List<Function>>
        get() = _functionsList.asLiveData()

    private val _navigateToFunctionFormEvent = MutableLiveData<Boolean>()
    val navigateToFunctionFormEvent: LiveData<Boolean>
        get () = _navigateToFunctionFormEvent

    init {
        _navigateToFunctionFormEvent.value = false
        val functions = functionRepository.geAllProjectById(1).asLiveData()
        Log.d("FunctionListViewModel", "Size: ${functions.value?.size}")
    }

    fun navigateToFunctionFormEventTriggered() {
        _navigateToFunctionFormEvent.value = true
    }

    fun navigateToFunctionFormEventDone() {
        _navigateToFunctionFormEvent.value = false
    }
}