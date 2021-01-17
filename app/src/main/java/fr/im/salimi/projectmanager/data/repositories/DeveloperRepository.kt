package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.DeveloperDao
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Developer

object DeveloperRepository : BaseRepository<Developer>(ProjectRoomDatabase.getInstance().developerDao()) {

    private val projectDao = dao as DeveloperDao

    fun getAllByProjectId(projectId: Long) =
            projectDao.getDevelopersByProject(projectId)
}