package fr.im.salimi.projectmanager.ui.editDeveloper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.im.salimi.projectmanager.data.entities.Developer

class EditDeveloperViewModel : ViewModel() {

    private val _developer = MutableLiveData<Developer>()
    val developer: LiveData<Developer>
        get() = _developer
}