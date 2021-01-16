package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.FeatureDao
import fr.im.salimi.projectmanager.data.entities.Feature

class FeatureRepository(private val dao: FeatureDao): BaseRepository<Feature>(dao) {

    fun geAllProjectById(projectId: Long) =
            dao.getAllByProjectId(projectId)
}