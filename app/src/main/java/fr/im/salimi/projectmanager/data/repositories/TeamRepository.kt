package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TeamDao
import fr.im.salimi.projectmanager.data.entities.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {

    suspend fun insert(team: Team) {
        teamDao.insert(team)
    }

    suspend fun delete(team: Team) {
        teamDao.delete(team)
    }

    suspend fun update(team: Team) {
        teamDao.update(team)
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