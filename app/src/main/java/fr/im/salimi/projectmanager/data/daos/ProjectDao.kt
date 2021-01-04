package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.entities.relations.ProjectWithModules
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProjectDao: BaseDao<Project> {

    @Query("SELECT * FROM projects WHERE project_id = :id")
    abstract override suspend fun getById(id: Long): Project

    @Query("SELECT * FROM projects")
    abstract override fun getAll(): Flow<List<Project>>

    @Query("SELECT * FROM projects")
    @Transaction
    abstract fun getAllWithModules(): Flow<List<ProjectWithModules>>

    @Query("SELECT * FROM projects WHERE project_id = :id")
    @Transaction
    abstract fun getByIdWithModules(id: Long): Flow<ProjectWithModules>
}