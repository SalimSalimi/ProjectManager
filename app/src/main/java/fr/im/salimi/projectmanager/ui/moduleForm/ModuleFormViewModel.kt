package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.core.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch
import java.util.*

class ModuleFormViewModel(private val id: Long)
    : ViewModel() {

    private val _module = MutableLiveData<Module>()
    val module: LiveData<Module>
        get() = _module

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addBtnClickEvent = MutableLiveData<Boolean>()
    val addBtnClickEvent: LiveData<Boolean>
        get() = _addBtnClickEvent

    val projects: LiveData<List<Project>>
        get() = ProjectRepository.getAll()

    private val _projectSelected = MutableLiveData<Project>()
    val projectSelected: LiveData<Project>
        get() = _projectSelected

    init {
        initModule()
        _dateClickEvent.value = false
        _addBtnClickEvent.value = false
    }

    fun onChooseDate(dates: Pair<Long, Long>) {
        _module.value!!.startingDate = Date(dates.first!!)
        _module.value!!.endingDate = Date(dates.second!!)
    }


    fun setProjectId(id: Long) {
        _module.value!!.projectId = id
    }

    fun onGetProjectById() {
        if (id != -1L)
            getProjectById()
    }

    fun onAddBtnClicked() {
        if (_module.value!!.projectId != -1L) {
            if (id == -1L)
                insert()
            else
                update()
            onAddBtnClickedEvent()
        }
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

    private fun initModule() {
        if (id == -1L)
            _module.value = Module()
        else
            viewModelScope.launch {
                _module.value = ModuleRepository.getById(id)
            }
    }

    private fun insert() {
        viewModelScope.launch {
            ModuleRepository.insert(_module.value!!)
        }
    }

    private fun update() {
        viewModelScope.launch {
            ModuleRepository.update(_module.value!!)
        }
    }

    private fun getProjectById() {
        viewModelScope.launch {
            _projectSelected.value = ProjectRepository.getById(_module.value!!.projectId)
        }
    }
}