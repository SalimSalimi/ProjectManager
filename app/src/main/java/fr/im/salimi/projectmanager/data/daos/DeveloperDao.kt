package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Developer
import kotlinx.coroutines.flow.Flow

@Dao
interface DeveloperDao {

    @Query("SELECT * FROM developers")
    suspend fun getAll(): Flow<List<Developer>>

    @Insert
    suspend fun insert(developer: Developer)

    @Delete
    suspend fun delete(developer: Developer)

    @Query("DELETE FROM developers")
    suspend fun deleteAll()

}