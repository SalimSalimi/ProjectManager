package fr.im.salimi.projectmanager.data.repositories.source

import fr.im.salimi.projectmanager.data.daos.BaseDao
import fr.im.salimi.projectmanager.data.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTasksDataSource(var tasksList: MutableList<Task>) : BaseDao<Task> {
    override suspend fun insert(entity: Task): Long {
        return if (tasksList.add(entity))
            1L
        else
            0L
    }

    override suspend fun delete(entity: Task) {
        tasksList.remove(entity)
    }

    override suspend fun update(entity: Task) {

    }

    override suspend fun getById(id: Long): Task {
        val task = tasksList.filter {
            it.id == id
        }
        return task[0]
    }

    override fun getAll(): Flow<List<Task>> {
        return flow<List<Task>> {
            emit(tasksList)
        }
    }
}