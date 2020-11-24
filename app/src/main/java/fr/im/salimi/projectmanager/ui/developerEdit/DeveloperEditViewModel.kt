package fr.im.salimi.projectmanager.ui.developerEdit

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

    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    private val _showSnackbarConfirm = MutableLiveData<Boolean>()
    val showSnackbarConfirm: LiveData<Boolean>
        get() = _showSnackbarConfirm

    init {
        _developer.value = Developer()
    }

    fun onAddClick() {
        insert(_developer.value!!)
    }

    private fun insert(developer: Developer) = viewModelScope.launch {
        repository.insert(developer)
        _showSnackbarConfirm.value = true
        _navigateToList.value = true
    }

    fun navigateToListDone() {
        _navigateToList.value = false
    }

    fun showSnackbarDone() {
        _showSnackbarConfirm.value = false
    }
}