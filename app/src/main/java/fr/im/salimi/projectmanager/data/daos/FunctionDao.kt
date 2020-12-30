package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Function
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FunctionDao: BaseDao<Function> {

    @Query("SELECT * FROM functions")
    abstract override fun getAll(): Flow<List<Function>>

    @Query("SELECT * FROM functions WHERE function_id= :id")
    abstract override suspend fun getById(id: Long): Function
}