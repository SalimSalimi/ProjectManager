package fr.im.salimi.projectmanager.ui.moduleList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository

class ModuleListViewModel(private val repository: ModuleRepository): ViewModel() {

    private val _modules = repository.getAll()
    val modules: LiveData<List<Module>>
        get() = _modules.asLiveData()

    private val _navigateToModuleFormEvent = MutableLiveData<Boolean>()
    val navigateToModuleFormEvent
        get() = _navigateToModuleFormEvent

    init {
        _navigateToModuleFormEvent.value = false
    }

    fun onAddFabBtnClicked() {
        _navigateToModuleFormEvent.value = true
    }
}