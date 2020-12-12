package fr.im.salimi.projectmanager.data.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    fun insert(entity: T)

    @Insert
    fun insertAll(vararg entity: T)

    @Delete
    fun delete(entity: T)

    @Update
    fun update(entity: T)
}