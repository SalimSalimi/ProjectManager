package fr.im.salimi.projectmanager.data.repositories

import androidx.lifecycle.LiveData
import fr.im.salimi.projectmanager.data.daos.DeveloperDao
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.helpers.Post

object DeveloperRepository :
        BaseRepository<Developer>(ProjectRoomDatabase.getInstance().developerDao()) {

    private val developerDao = dao as DeveloperDao

    fun getAllByProjectId(projectId: Long) =
            developerDao.getDevelopersByProject(projectId)

    fun getNumberDevelopersByPostByProjectId(projectId: Long) =
            developerDao.getNumberDevelopersByPostByProjectId(projectId)

    fun getDevelopersByPost(post: Post): LiveData<List<Developer>> =
            developerDao.getDevelopersByPost(post)

    suspend fun getById(developerId: Long) =
            developerDao
                    .getById(developerId)

    fun getAll() =
            developerDao.getAll()
}