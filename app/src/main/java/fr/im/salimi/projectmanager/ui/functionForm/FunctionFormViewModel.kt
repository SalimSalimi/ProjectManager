package fr.im.salimi.projectmanager.ui.functionForm

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.FunctionRepository
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import kotlinx.coroutines.launch
import java.util.*

class FunctionFormViewModel(private val id: Long,
                            private val repository: FunctionRepository,
                            private val moduleRepository: ModuleRepository) : ViewModel() {

    private val _function = MutableLiveData<Function>()
    val function: LiveData<Function>
        get() = _function

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addFabBtnClickEvent = MutableLiveData<Boolean>()
    val addFabBtnClickEvent: LiveData<Boolean>
        get() = _addFabBtnClickEvent

    private val _modulesList = moduleRepository.getAll()
    val modulesList: LiveData<List<Module>>
        get() = _modulesList.asLiveData()

    private val _module = MutableLiveData<Module>()
    val module: LiveData<Module>
        get() = _module

    init {
        initFunction()
        _addFabBtnClickEvent.value = false
        _dateClickEvent.value = false
    }

    fun setModuleId(id: Long) {
        _function.value!!.moduleId = id
    }

    fun onSetModule() {
        viewModelScope.launch {
            _module.value = moduleRepository.getById(_function.value!!.moduleId)
        }
    }

    fun onDateClickedEvent() {
        _dateClickEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickEvent.value = false
    }

    fun onAddFabBtnClickEvent() {
        _addFabBtnClickEvent.value = true
    }

    fun onAddFabClickedEventFinished() {
        _addFabBtnClickEvent.value = false
    }

    fun upsert() {
        if (id == -1L)
            insert()
        else
            update()
    }

    fun onChooseDate(startingDate: Date, endingDate: Date) {
        _function.value?.startingDate = startingDate
        _function.value?.endingDate = endingDate
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

    private fun insert() {
        viewModelScope.launch {
            repository.insert(_function.value!!)
        }
    }

    private fun update() {
        viewModelScope.launch {
            repository.update(_function.value!!)
        }
    }

}