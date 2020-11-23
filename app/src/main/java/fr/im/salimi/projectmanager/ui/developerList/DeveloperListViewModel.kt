package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository

class DeveloperListViewModel (repository: DeveloperRepository) : ViewModel() {

    val listDevelopers: LiveData<List<Developer>> = repository.allDevelopers.asLiveData()
}