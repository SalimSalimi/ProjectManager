package fr.im.salimi.projectmanager.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.relations.FeatureWithTasks
import fr.im.salimi.projectmanager.data.entities.subsets.FeatureState
import fr.im.salimi.projectmanager.data.entities.subsets.NumberByState

@Dao
abstract class FeatureDao: BaseDao<Feature> {

    @Query("SELECT * FROM features")
    abstract fun getAll(): LiveData<List<Feature>>

    @Query("SELECT * FROM features WHERE feature_id= :id")
    abstract suspend fun getById(id: Long): Feature

    @Query("SELECT * FROM features WHERE project_id_fk = :projectId")
    abstract fun getAllByProjectId(projectId: Long): LiveData<List<Feature>>

    @Query("SELECT * FROM features WHERE feature_id = :id")
    @Transaction
    abstract fun getByIdWithTasks(id: Long): LiveData<FeatureWithTasks>

    @Query("SELECT * FROM features")
    @Transaction
    abstract fun getAllWithTasks(): LiveData<FeatureWithTasks>

    @Query("SELECT f.feature_id, f.name, f.description, f.starting_date, f.finishing_date, f.project_id_fk, " +
            "f.module_id_fk ,MIN(t.state) as state " +
            "FROM tasks t, features f")
    abstract fun getAllFeaturesState(): LiveData<List<FeatureState>>

    @Query("SELECT f.feature_id, f.name, f.description, f.starting_date, f.finishing_date, " +
            "f.project_id_fk, f.module_id_fk, MIN(t.state) as state " +
            "FROM tasks t, features f " +
            "WHERE f.project_id_fk = :projectId")
    abstract fun getAllFeatureStateByProjectId(projectId: Long): LiveData<List<FeatureState>>

    @Query("SELECT f.feature_id, f.name, f.description, f.starting_date, f.finishing_date, " +
            "f.project_id_fk, f.module_id_fk ,MIN(t.state) as state " +
            "FROM tasks t, features f WHERE " +
            "t.feature_id_fk = :id")
    abstract fun getFeatureStateById(id: Long): LiveData<FeatureState>

    @Query("SELECT state AS state, COUNT(t.state) AS number " +
            "FROM tasks t " +
            "WHERE state = (" +
            "SELECT min(state) " +
            "FROM tasks tt " +
            "WHERE tt.feature_id_fk = t.feature_id_fk) " +
            "AND t.project_id_fk = :projectId " +
            "GROUP BY feature_id_fk")
    abstract fun getNumberStateByProjectId(projectId: Long): LiveData<List<NumberByState>>
}