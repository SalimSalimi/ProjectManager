package fr.im.salimi.projectmanager.ui.featureList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class FeatureListViewModelFactory(private val projectId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeatureListViewModel::class.java)) {
            return FeatureListViewModel(projectId) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}