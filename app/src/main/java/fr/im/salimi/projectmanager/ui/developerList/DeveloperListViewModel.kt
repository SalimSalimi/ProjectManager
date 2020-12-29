package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.launch


class DeveloperListViewModel (private val repository: DeveloperRepository) : ViewModel() {

    val listDevelopers: LiveData<List<Developer>> = repository.getAll().asLiveData()

    private val _onAddBtnClickEvent = MutableLiveData<Boolean>()
    val onAddBtnClickEvent: LiveData<Boolean>
        get() = _onAddBtnClickEvent

    init {
        _onAddBtnClickEvent.value = false
    }

    fun deleteDeveloper(developer: Developer) {
        viewModelScope.launch {
            repository.delete(developer)
        }
    }

    fun onAddBtnClickedEvent() {
        _onAddBtnClickEvent.value = true
    }

    fun onAddBtnClickedEventDone() {
        _onAddBtnClickEvent.value = false
    }
}
