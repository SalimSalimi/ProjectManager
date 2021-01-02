package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TaskDao
import fr.im.salimi.projectmanager.data.entities.Task

class TaskRepository(private val dao: TaskDao): BaseRepository<Task>(dao) {
}