package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class DeveloperListViewModel (private val projectId: Long, private val repository: DeveloperRepository) : ViewModel() {

    private val _listDevelopers: Flow<List<Developer>> = flow {
        if (projectId != -1L)
            emitAll(repository.getAllByProjectId(projectId))
        else
            emitAll(repository.getAll())
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
