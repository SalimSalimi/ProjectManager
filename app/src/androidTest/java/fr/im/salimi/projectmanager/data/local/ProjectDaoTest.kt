package fr.im.salimi.projectmanager.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class ProjectDaoTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ProjectRoomDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                ProjectRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()

    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertProject_getProjectById() = runBlocking {
        val startingDate = Date()
        val endingDate = Date()
        val project = Project(name = "project", description =  "Description", startingDate = startingDate, deadline = endingDate)
        database.projectDao().insert(project)

        //WHEN
        val result = database.projectDao().getById(1)

        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(result.name, Matchers.`is`("project"))
        MatcherAssert.assertThat(result.description, Matchers.`is`("Description"))
        MatcherAssert.assertThat(result.startingDate, Matchers.`is`(startingDate))
        MatcherAssert.assertThat(result.deadline, Matchers.`is`(endingDate))
    }

    @Test
    fun insertProjectWithModule_getProjectWithModules() = runBlocking {
        val startingDate = Date()
        val endingDate = Date()
        val project = Project(name = "project", description =  "Description", startingDate = startingDate, deadline = endingDate)
        database.projectDao().insert(project)

        val module = Module(name = "Module", description =  "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module)

        val module2 = Module(name = "Module2", description =  "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module2)

        //WHEN
        val result = database.projectDao().getByIdWithModules(1).asLiveData().getOrAwaitValue()

        val projectResult = result.project
        val modules = result.modules
        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(projectResult.name, Matchers.`is`("project"))
        MatcherAssert.assertThat(projectResult.description, Matchers.`is`("Description"))
        MatcherAssert.assertThat(projectResult.startingDate, Matchers.`is`(startingDate))
        MatcherAssert.assertThat(projectResult.deadline, Matchers.`is`(endingDate))


        MatcherAssert.assertThat(modules, IsNull.notNullValue())
        MatcherAssert.assertThat(modules.size, Matchers.`is`(2))
    }
}