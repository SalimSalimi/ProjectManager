package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.ModuleDao
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Module

object ModuleRepository : BaseRepository<Module>(ProjectRoomDatabase.getInstance().moduleDao()) {

    private val moduleDao = dao as ModuleDao
    
    fun getAllByProjectId(projectId: Long) =
            moduleDao.getAllByProjectId(projectId)

    fun getAllModuleState() =
            moduleDao.getAllModulesState()

    fun getAllModuleStateByProjectId(projectId: Long) =
            moduleDao.getAllModuleStateByProjectId(projectId)

    fun getModuleById(id: Long) =
            moduleDao.getModuleStateById(id)

    fun getNumberStateByProjectId(projectId: Long) =
            moduleDao.getNumberStateByProjectId(projectId)
}