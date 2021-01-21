package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.BaseDao

abstract class BaseRepository<T>(val dao: BaseDao<T>) {

    suspend fun insert(entity: T): Long {
        return dao.insert(entity)
    }

    suspend fun delete(entity: T) {
        dao.delete(entity)
    }

    suspend fun update(entity: T) {
        dao.update(entity)
    }

}