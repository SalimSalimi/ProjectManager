package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.FunctionDao
import fr.im.salimi.projectmanager.data.entities.Function

class FunctionRepository(private val dao: FunctionDao): BaseRepository<Function>(dao) {

    fun geAllProjectById(projectId: Long) =
            dao.getAllByProjectId(projectId)
}