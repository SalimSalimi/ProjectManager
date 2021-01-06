package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.core.util.Pair
import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import fr.im.salimi.projectmanager.data.repositories.ProjectRepository
import kotlinx.coroutines.launch
import java.util.*

class ModuleFormViewModel(private val id: Long, private val repository: ModuleRepository, private val projectRepository: ProjectRepository)
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

    private val _projects = projectRepository.getAll()
    val projects: LiveData<List<Project>>
        get() = _projects.asLiveData()

    init {
        initModule()
        _dateClickEvent.value = false
        _addBtnClickEvent.value = false
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

    private fun initModule() {
        if(id == -1L)
            _module.value = Module()
        else
            viewModelScope.launch {
                _module.value = repository.getById(id)
            }
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