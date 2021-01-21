package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.ProjectDao
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Project

object ProjectRepository: BaseRepository<Project>(ProjectRoomDatabase.getInstance().projectDao()) {

    private val projectDao = dao as ProjectDao

    fun getAll() =
            projectDao.getAll()

    suspend fun getById(id: Long) =
            projectDao.getById(id)

    fun getAllProjectState() =
            projectDao.getAllProjectState()

    fun getProjectStateById(id: Long) =
            projectDao.getProjectStateById(id)
}