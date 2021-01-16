package fr.im.salimi.projectmanager.ui.featureForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.im.salimi.projectmanager.data.repositories.FeatureRepository
import fr.im.salimi.projectmanager.data.repositories.ModuleRepository

@Suppress("UNCHECKED_CAST")
class FeatureFormViewModelFactory(private val id: Long, private val repository: FeatureRepository,
                                  private val moduleRepository: ModuleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeatureFormViewModel::class.java)) {
            return FeatureFormViewModel(id, repository, moduleRepository) as T
        }
        throw IllegalArgumentException("Uknown ViewModel Class")
    }
}