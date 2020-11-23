package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.DeveloperDao
import fr.im.salimi.projectmanager.data.entities.Developer

class DeveloperRepository(private val developerDao: DeveloperDao) {

    suspend fun insert(developer: Developer) {
        developerDao.insert(developer)
    }
}