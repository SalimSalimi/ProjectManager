package fr.im.salimi.projectmanager.ui.developerEdit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.im.salimi.projectmanager.data.entities.Developer

class DeveloperEditViewModel : ViewModel() {

    private val _developer = MutableLiveData<Developer>()
    val developer: LiveData<Developer>
        get() = _developer

    init {
        _developer.value = Developer()
    }

    fun onAddClick() {
        Log.d("DeveloperViewModel", _developer.value!!.toString())
        Log.d("DeveloperViewModel", _developer.value!!.post.toString())
    }
}