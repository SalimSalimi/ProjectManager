package fr.im.salimi.projectmanager.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments
import fr.im.salimi.projectmanager.data.entities.relations.TaskWithDevelopers
import fr.im.salimi.projectmanager.data.entities.subsets.NumberByState

@Dao
abstract class TaskDao: BaseDao<Task> {

    @Query("SELECT * FROM tasks")
    abstract fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE task_id = :id")
    abstract suspend fun getById(id: Long): Task

    @Query("SELECT * FROM tasks WHERE project_id_fk = :projectId")
    abstract fun getAllByProjectId(projectId: Long): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun assignTask(vararg taskAssignments: TaskAssignments)

    @Query("SELECT * FROM tasks WHERE task_id = :id")
    abstract suspend fun getTaskWithDevelopersByTaskId(id: Long): TaskWithDevelopers

    @Query("SELECT t.state, COUNT(t.state) as number FROM tasks t WHERE t.project_id_fk = :projectId GROUP BY t.state")
    abstract fun getNumberStateByProjectId(projectId: Long): LiveData<List<NumberByState>>
}