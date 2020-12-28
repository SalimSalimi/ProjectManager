package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.core.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import kotlinx.coroutines.launch
import java.util.*

class ModuleFormViewModel(private val id: Long, private val repository: ModuleRepository) : ViewModel() {

    private val _module = MutableLiveData<Module>()
    val module: LiveData<Module>
        get() = _module

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addBtnClickEvent = MutableLiveData<Boolean>()
    val addBtnClickEvent: LiveData<Boolean>
        get() = _addBtnClickEvent

    init {
        _dateClickEvent.value = false
        _module.value = Module()
    }

    fun onChooseDate(dates: Pair<Long, Long>) {
        _module.value!!.startingDate = Date(dates.first!!)
        _module.value!!.endingDate = Date(dates.second!!)
    }

    fun onAddBtnClicked() {
        if (id == -1L)
            insert()
        else
            update()
        onAddBtnClickedEvent()
    }

    fun onDateClickedEvent() {
        _dateClickEvent.value = true
    }

    private fun onAddBtnClickedEvent() {
        _addBtnClickEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickEvent.value = false
    }

    fun onAddBtnClickedFinished() {
        _addBtnClickEvent.value = false
    }

    private fun insert() {
        viewModelScope.launch {
            repository.insert(_module.value!!)
        }
    }

    private fun update() {
        viewModelScope.launch {
            repository.update(_module.value!!)
        }
    }
}