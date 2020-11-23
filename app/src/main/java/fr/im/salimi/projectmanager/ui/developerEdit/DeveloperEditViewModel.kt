package fr.im.salimi.projectmanager.ui.developerEdit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.launch

class DeveloperEditViewModel(private val repository: DeveloperRepository) : ViewModel() {

    private val _developer = MutableLiveData<Developer>()
    val developer: LiveData<Developer>
        get() = _developer

    init {
        _developer.value = Developer()
    }

    fun onAddClick() {
        Log.d("DeveloperViewModel", "Clicked")
        insert(_developer.value!!)
    }

    private fun insert(developer: Developer) = viewModelScope.launch {
        repository.insert(developer)
    }
}