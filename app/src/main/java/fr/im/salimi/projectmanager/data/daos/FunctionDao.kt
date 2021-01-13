package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.entities.relations.FunctionWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FunctionDao: BaseDao<Function> {

    @Query("SELECT * FROM functions")
    abstract override fun getAll(): Flow<List<Function>>

    @Query("SELECT * FROM functions WHERE function_id= :id")
    abstract override suspend fun getById(id: Long): Function

    @Query("SELECT f.function_id, f.name, f.description, f.module_id_fk,f.starting_date, f.finishing_date " +
            "FROM functions f " +
            "INNER JOIN modules m ON m.module_id = f.module_id_fk " +
            "INNER JOIN projects p on p.project_id = :projectId")
    abstract fun getAllByProjectId(projectId: Long): Flow<List<Function>>

    @Query("SELECT * FROM functions WHERE function_id = :id")
    @Transaction
    abstract fun getByIdWithTasks(id: Long): Flow<FunctionWithTasks>

    @Query("SELECT * FROM functions")
    @Transaction
    abstract fun getAllWithTasks(): Flow<FunctionWithTasks>
}