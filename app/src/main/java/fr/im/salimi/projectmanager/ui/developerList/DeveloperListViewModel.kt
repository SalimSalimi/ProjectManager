package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.launch


class DeveloperListViewModel (private val repository: DeveloperRepository) : ViewModel() {

    val listDevelopers: LiveData<List<Developer>> = repository.allDevelopers.asLiveData()

    fun deleteDeveloper(developer: Developer) {
        viewModelScope.launch {
            repository.deleteDeveloper(developer)
        }
    }
}
