package fr.im.salimi.projectmanager.data.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {
    @Insert
    suspend fun insert(entity: T)

    @Delete
    suspend fun delete(entity: T)

    @Update
    suspend fun update(entity: T)

    suspend fun getById(id: Long): T

    fun getAll(): Flow<List<T>>
}