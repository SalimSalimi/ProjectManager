package fr.im.salimi.projectmanager.ui.featureForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.FeatureRepository
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import kotlinx.coroutines.launch
import java.util.*

class FeatureFormViewModel(private val id: Long) : ViewModel() {

    private val _function = MutableLiveData<Feature>()
    val feature: LiveData<Feature>
        get() = _function

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addFabBtnClickEvent = MutableLiveData<Boolean>()
    val addFabBtnClickEvent: LiveData<Boolean>
        get() = _addFabBtnClickEvent

    private val _modulesList = ModuleRepository.getAll()
    val modulesList: LiveData<List<Module>>
        get() = _modulesList

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

    fun setProjectId(id: Long) {
        _function.value!!.projectId = id
    }

    fun onSetModule() {
        viewModelScope.launch {
            _module.value = ModuleRepository.getById(_function.value!!.moduleId)
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
            _function.value = Feature()
        else
            getFunctionById()
    }

    private fun getFunctionById() {
        viewModelScope.launch {
            _function.value = FeatureRepository.getById(id)
        }
    }

    private fun insert() {
        viewModelScope.launch {
            FeatureRepository.insert(_function.value!!)
        }
    }

    private fun update() {
        viewModelScope.launch {
            FeatureRepository.update(_function.value!!)
        }
    }

}