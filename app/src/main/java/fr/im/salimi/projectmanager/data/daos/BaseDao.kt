package fr.im.salimi.projectmanager.data.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    suspend fun insert(entity: T): Long

    @Delete
    suspend fun delete(entity: T)

    @Update
    suspend fun update(entity: T)

}