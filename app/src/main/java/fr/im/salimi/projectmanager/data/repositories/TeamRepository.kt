package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TeamDao
import fr.im.salimi.projectmanager.data.entities.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao): BaseRepository<Team>(teamDao) {

    override suspend fun getById(id: Long): Team =
            teamDao.getTeamById(id)

    override fun getAll(): Flow<List<Team>> =
            teamDao.getAll()

}