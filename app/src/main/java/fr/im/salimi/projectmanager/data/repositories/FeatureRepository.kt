package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.FeatureDao
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Feature

object FeatureRepository : BaseRepository<Feature>(ProjectRoomDatabase.getInstance().featureDao()) {

    private val featureDao = dao as FeatureDao

    fun geAllByProjectId(projectId: Long) =
            featureDao.getAllByProjectId(projectId)

    fun getAllFeaturesState() =
            featureDao.getAllFeaturesState()

    fun getFeatureStateById(id: Long) =
            featureDao.getFeatureStateById(id)

    fun getAllFeatureStateByProjectId(projectId: Long) =
            featureDao.getAllFeatureStateByProjectId(projectId)

    fun getNumberStateByProjectId(projectId: Long) =
            featureDao.getNumberStateByProjectId(projectId)

}