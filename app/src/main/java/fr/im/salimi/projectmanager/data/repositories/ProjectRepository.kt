package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.ProjectDao
import fr.im.salimi.projectmanager.data.entities.Project

class ProjectRepository(private val projectDao: ProjectDao):BaseRepository<Project>(projectDao)