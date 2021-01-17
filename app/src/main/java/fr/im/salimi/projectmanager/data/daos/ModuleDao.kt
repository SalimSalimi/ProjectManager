package fr.im.salimi.projectmanager.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.relations.ModuleWithFeatures
import fr.im.salimi.projectmanager.data.entities.subsets.ModuleState
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ModuleDao: BaseDao<Module> {

    @Query("SELECT * FROM modules WHERE module_id = :id")
    abstract override suspend fun getById(id: Long): Module

    @Query("SELECT * FROM modules")
    abstract override fun getAll(): Flow<List<Module>>

    @Query("SELECT * FROM modules WHERE project_id_fk = :projectId")
    abstract fun getAllByProjectId(projectId: Long): Flow<List<Module>>

    @Query("SELECT * FROM modules")
    @Transaction
    abstract fun getAllWithFeatures(): Flow<List<ModuleWithFeatures>>

    @Query("SELECT * FROM modules where module_id = :id")
    @Transaction
    abstract fun getByIdWithFeatures(id: Long): Flow<ModuleWithFeatures>

    @Query("SELECT m.module_id, m.name, m.description, m.starting_date, m.finishing_date, m.project_id_fk, " +
            "MIN(t.state) as state " +
            "FROM modules m, tasks t " +
            "WHERE t.project_id_fk = m.project_id_fk")
    abstract fun getAllModulesState(): Flow<List<ModuleState>>

    @Query("SELECT m.module_id, m.name, m.description, m.starting_date, m.finishing_date, m.project_id_fk, " +
            "MIN(t.state) as state " +
            "FROM modules m, tasks t " +
            "WHERE t.project_id_fk = m.project_id_fk " +
            "AND module_id = :id")
    abstract fun getModuleStateById(id: Long): LiveData<ModuleState>

    @Query("SELECT m.module_id, m.name, m.description, m.starting_date, m.finishing_date, m.project_id_fk, " +
            "MIN(t.state) as state " +
            "FROM modules m, tasks t " +
            "WHERE t.project_id_fk = :projectId " +
            "AND m.project_id_fk = :projectId")
    abstract fun getAllModuleStateByProjectId(projectId: Long): Flow<List<ModuleState>>
}