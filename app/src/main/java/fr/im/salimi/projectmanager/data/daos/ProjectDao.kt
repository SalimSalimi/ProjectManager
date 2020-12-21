package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import fr.im.salimi.projectmanager.data.entities.Project
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProjectDao: BaseDao<Project> {

    @Query("SELECT * FROM projects WHERE project_id = :id")
    abstract override suspend fun getById(id: Long): Project

    @Query("SELECT * FROM projects")
    abstract override fun getAll(): Flow<List<Project>>

}