package fr.im.salimi.projectmanager.ui.functionForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
import kotlinx.coroutines.launch

class FunctionFormViewModel(private val id: Long, private val repository: FunctionRepository) : ViewModel() {

    private val _function = MutableLiveData<Function>()
    val function: LiveData<Function>
        get() = _function

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addFabBtnClickEvent = MutableLiveData<Boolean>()
    val addFabBtnClickEvent: LiveData<Boolean>
        get() = _addFabBtnClickEvent

    init {
        initFunction()
        _addFabBtnClickEvent.value = false
        _dateClickEvent.value = false
    }

    fun onDateClickedEvent() {
        _dateClickEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickEvent.value = false
    }

    fun onAddFabBtnClicked() {
        _addFabBtnClickEvent.value = true
    }

    fun onAddFabClickedEventFinished() {
        _addFabBtnClickEvent.value = false
    }

    private fun initFunction() {
        if (id == -1L)
            _function.value = Function()
        else
            getFunctionById()
    }

    private fun getFunctionById() {
        viewModelScope.launch {
            _function.value = repository.getById(id)
        }
    }
}