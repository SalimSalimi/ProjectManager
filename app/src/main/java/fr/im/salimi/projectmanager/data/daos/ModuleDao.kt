package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Module
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ModuleDao: BaseDao<Module> {

    @Query("SELECT * FROM modules WHERE module_id = :id")
    abstract override suspend fun getById(id: Long): Module

    @Query("SELECT * FROM modules")
    abstract override fun getAll(): Flow<List<Module>>
}