package fr.im.salimi.projectmanager.ui.featureForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class FeatureFormViewModelFactory(private val id: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeatureFormViewModel::class.java)) {
            return FeatureFormViewModel(id) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}