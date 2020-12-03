package fr.im.salimi.projectmanager.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.im.salimi.projectmanager.data.entities.Developer
import kotlinx.coroutines.flow.Flow

@Dao
interface DeveloperDao {

    @Query("SELECT * FROM developers")
    fun getAll(): Flow<List<Developer>>

    @Query("SELECT * FROM developers WHERE id = :id")
    suspend fun getById(id: Long): Developer

    @Insert
    suspend fun insert(developer: Developer)

    @Update
    suspend fun update(vararg developer: Developer)

    @Delete
    suspend fun delete(developer: Developer)

    @Query("DELETE FROM developers")
    suspend fun deleteAll()

}