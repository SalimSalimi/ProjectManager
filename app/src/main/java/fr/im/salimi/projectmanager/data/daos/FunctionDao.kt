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

    @Query("SELECT * FROM functions WHERE function_id = :id")
    @Transaction
    abstract fun getByIdWithTasks(id: Long): Flow<FunctionWithTasks>

    @Query("SELECT * FROM functions")
    @Transaction
    abstract fun getAllWithTasks(): Flow<FunctionWithTasks>
}