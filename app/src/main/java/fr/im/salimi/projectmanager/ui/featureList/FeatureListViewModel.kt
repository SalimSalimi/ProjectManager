package fr.im.salimi.projectmanager.ui.featureList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.subsets.FeatureState
import fr.im.salimi.projectmanager.data.repositories.FeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FeatureListViewModel(private val projectId: Long) : ViewModel() {

    private val _featuresList: Flow<List<FeatureState>> = flow {
        if (projectId == -1L)
            emitAll(FeatureRepository.getAllFeaturesState())
        else
            emitAll(FeatureRepository.getAllFeatureStateByProjectId(projectId))
    }

    val featuresList: LiveData<List<FeatureState>>
        get() = _featuresList.asLiveData()

    private val _navigateToFeatureFormEvent = MutableLiveData<Boolean>()
    val navigateToFeatureFormEvent: LiveData<Boolean>
        get() = _navigateToFeatureFormEvent

    init {
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