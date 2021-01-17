package fr.im.salimi.projectmanager.ui.developerEdit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.launch

class DeveloperEditViewModel(private val id: Long) : ViewModel() {

    private val _developer = MutableLiveData<Developer>()
    val developer: LiveData<Developer>
        get() = _developer

    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    private val _showSnackbarConfirm = MutableLiveData<Boolean>()
    val showSnackbarConfirm: LiveData<Boolean>
        get() = _showSnackbarConfirm

    init {
        initDeveloper()
    }

    fun onAddClick() {
        upsert(_developer.value!!)
    }

    private fun upsert(developer: Developer) = viewModelScope.launch {
        if (id == -1L) {
            DeveloperRepository.insert(developer)
        } else {
            DeveloperRepository.update(developer)
        }
        _showSnackbarConfirm.value = true
        _navigateToList.value = true
    }

    private fun getDeveloperById(id: Long) {
        viewModelScope.launch {
            val developer = DeveloperRepository.getById(id)
            _developer.postValue(developer)
            Log.d("DeveloperEditViewModel", "Get developers by id executed ${_developer.value?.firstName}")
        }
    }

    private fun initDeveloper() {
        if (id == -1L) {
            _developer.value = Developer()
        } else {
            getDeveloperById(id)
        }
    }

    fun navigateToListDone() {
        _navigateToList.value = false
    }

    fun showSnackbarDone() {
        _showSnackbarConfirm.value = false
    }
}