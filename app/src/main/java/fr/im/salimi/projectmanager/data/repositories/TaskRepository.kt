package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TaskDao
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments

object TaskRepository: BaseRepository<Task>(ProjectRoomDatabase.getInstance().taskDao()) {

    private val taskDao = dao as TaskDao

    fun getAll() =
            taskDao.getAll()

    suspend fun getById(id: Long) =
            taskDao.getById(id)

    suspend fun insertTaskAssignments(taskAssignments: List<TaskAssignments>) {
        taskDao.assignTask(*taskAssignments.map { it }.toTypedArray())
    }

    suspend fun getTaskAssignmentsByTaskId(id: Long) =
            taskDao.getTaskWithDevelopersByTaskId(id)

    fun getAllByProjectId(projectId: Long) =
            taskDao.getAllByProjectId(projectId)

    fun getNumberStateByProjectId(projectId: Long) =
            taskDao.getNumberStateByProjectId(projectId)
}