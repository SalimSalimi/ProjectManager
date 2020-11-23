package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.DeveloperDao
import fr.im.salimi.projectmanager.data.entities.Developer
import kotlinx.coroutines.flow.Flow

class DeveloperRepository(private val developerDao: DeveloperDao) {

    val developers: Flow<List<Developer>> = developerDao.getAll()

    suspend fun insert(developer: Developer) {
        developerDao.insert(developer)
    }
}