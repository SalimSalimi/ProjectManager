package fr.im.salimi.projectmanager.ui.featureList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.repositories.FeatureRepository
import kotlinx.coroutines.launch

class FeatureListViewModel(private val projectId: Long) : ViewModel() {

    private val _id = MutableLiveData<Long>()

    val featuresList: LiveData<List<Feature>> = Transformations.switchMap(_id) { projectId ->
        if (projectId == -1L)
            FeatureRepository.getAll()
        else
            FeatureRepository.geAllByProjectId(projectId)
    }

    private val _navigateToFeatureFormEvent = MutableLiveData<Boolean>()
    val navigateToFeatureFormEvent: LiveData<Boolean>
        get() = _navigateToFeatureFormEvent

    init {
        _id.value = projectId
        _navigateToFeatureFormEvent.value = false
    }

    fun navigateToFeatureFormEventTriggered() {
        _navigateToFeatureFormEvent.value = true
    }

    fun navigateToFeatureFormEventDone() {
        _navigateToFeatureFormEvent.value = false
    }

    fun deleteFeature(feature: Feature) {
        viewModelScope.launch {
            FeatureRepository.delete(feature)
        }
    }
}