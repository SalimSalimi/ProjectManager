package fr.im.salimi.projectmanager.ui.moduleList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.subsets.ModuleState
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ModuleListViewModel(private val projectId: Long) :
    ViewModel() {

    private val _modules: Flow<List<ModuleState>> = flow {
        if (projectId != -1L)
            emitAll(ModuleRepository.getAllModuleStateByProjectId(projectId))
        else
            emitAll(ModuleRepository.getAllModuleState())
    }

    val modules: LiveData<List<ModuleState>>
        get() = _modules.asLiveData()

    private val _navigateToModuleFormEvent = MutableLiveData<Boolean>()
    val navigateToModuleFormEvent
        get() = _navigateToModuleFormEvent

    init {
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

    private fun initModules() {
        _modules
    }

    fun deleteModule(module: Module) {
        viewModelScope.launch {
            ModuleRepository.delete(module)
        }
    }
}