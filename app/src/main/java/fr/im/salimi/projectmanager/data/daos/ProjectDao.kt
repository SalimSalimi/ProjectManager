package fr.im.salimi.projectmanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.entities.relations.ProjectWithModules
import fr.im.salimi.projectmanager.data.entities.subsets.ProjectState
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

    @Query("SELECT p.project_id, p.project_name, p.starting_date, p.description, p.deadline, p.customer, MIN(t.state) as state FROM projects p, tasks t WHERE t.project_id_fk = p.project_id")
    abstract fun getAllProjectState(): Flow<List<ProjectState>>

    @Query("SELECT p.project_id, p.project_name, p.starting_date, p.description, p.deadline, p.customer, MIN(t.state) as state FROM projects p, tasks t WHERE t.project_id_fk = :projectId")
    abstract fun getProjectStateById(projectId: Long): Flow<ProjectState>
}