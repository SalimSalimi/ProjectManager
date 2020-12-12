package fr.im.salimi.projectmanager.data.daos

import androidx.room.*
import fr.im.salimi.projectmanager.data.entities.Team
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TeamDao: BaseDao<Team> {

    @Query("SELECT * FROM teams")
    abstract fun getAll(): Flow<List<Team>>

    @Query("SELECT * FROM teams WHERE team_id = :id")
    abstract suspend fun getTeamById(id: Long): Team

    @Query("DELETE FROM teams")
    abstract suspend fun deleteAll()
}