package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.DeveloperDao
import fr.im.salimi.projectmanager.data.entities.Developer
import kotlinx.coroutines.flow.Flow

class DeveloperRepository(private val developerDao: DeveloperDao): BaseRepository<Developer>(developerDao) {

    override suspend fun getById(id: Long): Developer {
        return developerDao.getById(id)
    }

    override fun getAll(): Flow<List<Developer>> {
        return developerDao.getAll()
    }
}