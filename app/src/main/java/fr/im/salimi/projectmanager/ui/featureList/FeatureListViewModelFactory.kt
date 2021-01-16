package fr.im.salimi.projectmanager.ui.featureList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.FeatureRepository

@Suppress("UNCHECKED_CAST")
class FeatureListViewModelFactory(private val projectId: Long, private val repository: FeatureRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeatureListViewModel::class.java)) {
            return FeatureListViewModel(projectId, repository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}