package fr.im.salimi.projectmanager.ui.moduleForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.im.salimi.projectmanager.data.entities.Module

class ModuleFormViewModel : ViewModel() {

    private val _module = MutableLiveData<Module>()
    val module: LiveData<Module>
        get() = _module

    init {
        _module.value = Module()
    }

    fun onAddBtnClicked() {

    }

    fun onDateClickedEvent() {

    }
}