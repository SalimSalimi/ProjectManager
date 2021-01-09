package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.TaskAssignments
import fr.im.salimi.projectmanager.data.entities.relations.DeveloperWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DeveloperDao: BaseDao<Developer> {

    @Query("SELECT * FROM developers")
    abstract override fun getAll(): Flow<List<Developer>>

    @Query("SELECT * FROM developers WHERE developer_id = :id")
    abstract override suspend fun getById(id: Long): Developer

    @Query("DELETE FROM developers")
    abstract suspend fun deleteAll()

    @Insert
    abstract fun assignTask(taskAssignments: TaskAssignments)

    @Query("SELECT * FROM developers WHERE developer_id =:id")
    abstract suspend fun getDeveloperWithTasksById(id: Long): DeveloperWithTasks
}