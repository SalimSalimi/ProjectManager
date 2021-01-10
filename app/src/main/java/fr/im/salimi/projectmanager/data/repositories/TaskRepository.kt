package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TaskDao
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments

class TaskRepository(private val dao: TaskDao): BaseRepository<Task>(dao) {

    suspend fun insertTaskAssignments(taskAssignments: List<TaskAssignments>) {
        dao.assignTask(*taskAssignments.map { it }.toTypedArray())
    }
}