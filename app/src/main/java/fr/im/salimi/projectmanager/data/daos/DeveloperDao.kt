package fr.im.salimi.projectmanager.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.relations.DeveloperWithTasks
import fr.im.salimi.projectmanager.data.entities.subsets.NumberByPost
import fr.im.salimi.projectmanager.data.helpers.Post

@Dao
abstract class DeveloperDao: BaseDao<Developer> {

    @Query("SELECT * FROM developers")
    abstract fun getAll(): LiveData<List<Developer>>

    @Query("SELECT * FROM developers WHERE developer_id = :id")
    abstract suspend fun getById(id: Long): Developer

    @Query("SELECT d.developer_id, d.first_name, d.last_name, d.city, d.country, d.email, d.phone_number, " +
            "d.post, d.postalCode, d.roadName, d.roadNumber " +
            "FROM developers d JOIN tasks_assignments ta ON d.developer_id = ta.task_id " +
            "JOIN tasks t ON t.task_id = ta.task_id " +
            "AND t.project_id_fk = :projectId")
    abstract fun getDevelopersByProject(projectId: Long): LiveData<List<Developer>>

    @Query("DELETE FROM developers")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM developers WHERE developer_id =:id")
    abstract suspend fun getDeveloperWithTasksById(id: Long): DeveloperWithTasks

    @Query("SELECT post, COUNT(post) as number FROM developers d WHERE developer_id = (SELECT d.developer_id FROM tasks_assignments ta, tasks t WHERE d.developer_id = ta.developer_id AND ta.task_id = t.task_id AND t.project_id_fk = :projectId) GROUP BY post")
    @Transaction
    abstract fun getNumberDevelopersByPostByProjectId(projectId: Long): LiveData<List<NumberByPost>>

    @Query("SELECT * FROM developers WHERE post = :post")
    abstract fun getDevelopersByPost(post: Post): LiveData<List<Developer>>

    @Query("SELECT * FROM developers d WHERE post = :post AND developer_id = (SELECT d.developer_id FROM tasks_assignments ta, tasks t WHERE d.developer_id = ta.developer_id AND ta.task_id = t.task_id AND t.project_id_fk = :projectId)")
    abstract fun getDevelopersByPostAndProjectId(post: Post, projectId: Long): LiveData<List<Developer>>
}