package fr.im.salimi.projectmanager.data.repositories.source

import fr.im.salimi.projectmanager.data.daos.BaseDao
import fr.im.salimi.projectmanager.data.entities.Module
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeModuleDataSource(var modulesList: MutableList<Module> = mutableListOf()):BaseDao<Module> {
    override suspend fun insert(entity: Module) {
        modulesList.add(entity)
    }

    override suspend fun delete(entity: Module) {
        modulesList.remove(entity)
    }

    override suspend fun update(entity: Module) {

    }

    override suspend fun getById(id: Long): Module {
        val module = modulesList.filter {
            it.id == id
        }
        return module[0]
    }

    override fun getAll(): Flow<List<Module>> {
        return flow<List<Module>> {
            emit(modulesList)
        }
    }
}