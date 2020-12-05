package fr.im.salimi.projectmanager.data.daos

import androidx.room.*
import fr.im.salimi.projectmanager.data.entities.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Insert
    suspend fun addTeam(team: Team)

    @Update
    suspend fun updateTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Query("SELECT * FROM teams")
    fun getAll(): Flow<List<Team>>

    @Query("SELECT * FROM teams WHERE team_id = :id")
    suspend fun getTeamById(id: Long): Team

    @Query("DELETE FROM teams")
    suspend fun deleteAll()
}