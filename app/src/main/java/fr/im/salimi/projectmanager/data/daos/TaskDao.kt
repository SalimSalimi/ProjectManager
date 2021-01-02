package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TaskDao: BaseDao<Task> {

    @Query("SELECT * FROM tasks")
    abstract override fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE task_id = :id")
    abstract override suspend fun getById(id: Long): Task
}