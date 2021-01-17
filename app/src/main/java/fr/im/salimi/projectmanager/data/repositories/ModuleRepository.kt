package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.ModuleDao
import fr.im.salimi.projectmanager.data.entities.Module

class ModuleRepository(private val dao: ModuleDao) : BaseRepository<Module>(dao) {

    fun getAllByProjectId(projectId: Long) =
            dao.getAllByProjectId(projectId)

    fun getAllModuleState() =
            dao.getAllModulesState()

    fun getAllModuleStateByProjectId(projectId: Long) =
            dao.getAllModuleStateByProjectId(projectId)

    fun getModuleById(id: Long) =
            dao.getModuleStateById(id)
}