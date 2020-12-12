package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.BaseDao
import kotlinx.coroutines.flow.Flow

abstract class BaseRepository<T>(private val dao: BaseDao<T>) {

    suspend fun insert(entity: T) {
        dao.insert(entity)
    }

    suspend fun delete(entity: T) {
        dao.delete(entity)
    }

    suspend fun update(entity: T) {
        dao.update(entity)
    }

    abstract fun getAll(): Flow<List<T>>

    abstract suspend fun getById(id: Long): T
}