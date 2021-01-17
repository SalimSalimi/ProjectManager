package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.ProjectDao
import fr.im.salimi.projectmanager.data.entities.Project

class ProjectRepository(private val dao: ProjectDao):BaseRepository<Project>(dao) {

    fun getAllProjectState() =
            dao.getAllProjectState()

    fun getProjectStateById(id: Long) =
            dao.getProjectStateById(id)
}