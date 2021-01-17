package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.FeatureDao
import fr.im.salimi.projectmanager.data.entities.Feature

class FeatureRepository(private val dao: FeatureDao): BaseRepository<Feature>(dao) {

    fun geAllByProjectId(projectId: Long) =
            dao.getAllByProjectId(projectId)

    fun getAllFeaturesState() =
            dao.getAllFeaturesState()

    fun getFeatureStateById(id: Long) =
            dao.getFeatureStateById(id)

    fun getAllFeatureStateByProjectId(projectId: Long) =
            dao.getAllFeatureStateByProjectId(projectId)

    fun getNumberStateByProjectId(projectId: Long) =
            dao.getNumberStateByProjectId(projectId)
}