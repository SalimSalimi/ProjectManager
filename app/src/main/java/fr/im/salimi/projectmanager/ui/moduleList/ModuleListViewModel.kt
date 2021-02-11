package fr.im.salimi.projectmanager.ui.moduleList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import kotlinx.coroutines.launch

class ModuleListViewModel(private val projectId: Long) :
    ViewModel() {

    private val _id = MutableLiveData<Long>()

    val modules: LiveData<List<Module>> = Transformations.switchMap(_id) {
        if (projectId != -1L)
            ModuleRepository.getAllByProjectId(projectId)
        else
            ModuleRepository.getAll()
    }

    private val _navigateToModuleFormEvent = MutableLiveData<Boolean>()
    val navigateToModuleFormEvent
        get() = _navigateToModuleFormEvent

    init {
        _id.value = projectId
        _navigateToModuleFormEvent.value = false
    }

    fun onAddFabBtnClicked() {
        onNavigateToModuleFormEvent()
    }

    fun onNavigateToModuleFormEventTriggered() {
        _navigateToModuleFormEvent.value = false
    }

    private fun onNavigateToModuleFormEvent() {
        _navigateToModuleFormEvent.value = true
    }

    fun deleteModule(module: Module) {
        viewModelScope.launch {
            ModuleRepository.delete(module)
        }
    }
}