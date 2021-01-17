package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class DeveloperListViewModel (private val projectId: Long) : ViewModel() {

    private val _listDevelopers: Flow<List<Developer>> = flow {
        if (projectId != -1L)
            emitAll(DeveloperRepository.getAllByProjectId(projectId))
        else
            emitAll(DeveloperRepository.getAll())
    }
    val listDevelopers: LiveData<List<Developer>>
        get() = _listDevelopers.asLiveData()

    private val _onAddBtnClickEvent = MutableLiveData<Boolean>()
    val onAddBtnClickEvent: LiveData<Boolean>
        get() = _onAddBtnClickEvent

    init {
        _onAddBtnClickEvent.value = false
    }

    fun deleteDeveloper(developer: Developer) {
        viewModelScope.launch {
            DeveloperRepository.delete(developer)
        }
    }

    fun onAddBtnClickedEvent() {
        _onAddBtnClickEvent.value = true
    }

    fun onAddBtnClickedEventDone() {
        _onAddBtnClickEvent.value = false
    }
}
