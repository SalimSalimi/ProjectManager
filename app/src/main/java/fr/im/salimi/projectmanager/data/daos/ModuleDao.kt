package fr.im.salimi.projectmanager.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.relations.ModuleWithFeatures
import fr.im.salimi.projectmanager.data.entities.subsets.ModuleState
import fr.im.salimi.projectmanager.data.entities.subsets.NumberByState

@Dao
abstract class ModuleDao: BaseDao<Module> {

    @Query("SELECT * FROM modules WHERE module_id = :id")
    abstract suspend fun getById(id: Long): Module

    @Query("SELECT * FROM modules")
    abstract fun getAll(): LiveData<List<Module>>

    @Query("SELECT * FROM modules WHERE project_id_fk = :projectId")
    abstract fun getAllByProjectId(projectId: Long): LiveData<List<Module>>

    @Query("SELECT * FROM modules")
    @Transaction
    abstract fun getAllWithFeatures(): LiveData<List<ModuleWithFeatures>>

    @Query("SELECT * FROM modules where module_id = :id")
    @Transaction
    abstract fun getByIdWithFeatures(id: Long): LiveData<ModuleWithFeatures>

    @Query("SELECT m.module_id, m.name, m.description, m.starting_date, m.finishing_date, m.project_id_fk, " +
            "MIN(t.state) as state " +
            "FROM modules m, tasks t " +
            "WHERE t.project_id_fk = m.project_id_fk")
    abstract fun getAllModulesState(): LiveData<List<ModuleState>>

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
    abstract fun getAllModuleStateByProjectId(projectId: Long): LiveData<List<ModuleState>>

    @Query("SELECT state AS state, COUNT(t.state) AS number FROM tasks t WHERE state = (SELECT min(state) FROM tasks tt WHERE tt.feature_id_fk = t.feature_id_fk) AND t.project_id_fk = :projectId GROUP BY feature_id_fk")
    abstract fun getNumberStateByProjectId(projectId: Long): LiveData<List<NumberByState>>
}