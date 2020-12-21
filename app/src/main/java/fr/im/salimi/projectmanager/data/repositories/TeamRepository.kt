package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.TeamDao
import fr.im.salimi.projectmanager.data.entities.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao): BaseRepository<Team>(teamDao)