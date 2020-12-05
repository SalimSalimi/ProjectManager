package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TeamDao
import fr.im.salimi.projectmanager.data.entities.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {

    suspend fun insert(team: Team) {
        teamDao.addTeam(team)
    }

    suspend fun delete(team: Team) {
        teamDao.deleteTeam(team)
    }

    fun getAll(): Flow<List<Team>> =
        teamDao.getAll()

    suspend fun getById(id: Long) {
        teamDao.getTeamById(id)
    }

    suspend fun deleteAll() {
        teamDao.deleteAll()
    }
}