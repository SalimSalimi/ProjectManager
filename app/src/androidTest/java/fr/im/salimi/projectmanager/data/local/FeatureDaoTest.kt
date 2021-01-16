package fr.im.salimi.projectmanager.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.helpers.State
import fr.im.salimi.projectmanager.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class FeatureDaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ProjectRoomDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                ProjectRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        runBlocking {
            database.projectDao().insert(Project(name = "Project", description = "Description"))
            database.moduleDao().insert(Module(name = "Module", description = "Description", projectId = 1L))
        }
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertFeatureAndGetById() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val feature = Feature(name = "Feature", description = "Description", startingDate = startingDate, endingDate = endingDate, featureId = 1 ,projectId = 1, moduleId = 1)
        database.featureDao().insert(feature)

        //WHEN
        val result = database.featureDao().getById(1)

        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(result.name, Matchers.`is`("Feature"))
        MatcherAssert.assertThat(result.description, Matchers.`is`("Description"))
        MatcherAssert.assertThat(result.startingDate, Matchers.`is`(startingDate))
        MatcherAssert.assertThat(result.endingDate, Matchers.`is`(endingDate))
    }

    @Test
    fun insertAndUpdateFeatureAndGetById() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val feature = Feature(name = "Feature", description = "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1, moduleId = 1)
        database.featureDao().insert(feature)

        //WHEN
        val updatedStartingDate = Date()
        val updatedEndingDate = Date()
        val updatedFeature = Feature(featureId = 1, name = "Updated", description = "UpdatedDescription", updatedStartingDate, updatedEndingDate, projectId = 1, moduleId = 1)
        database.featureDao().update(updatedFeature)
        val result = database.featureDao().getById(1)

        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(result.name, Matchers.`is`("Updated"))
        MatcherAssert.assertThat(result.description, Matchers.`is`("UpdatedDescription"))
        MatcherAssert.assertThat(result.startingDate, Matchers.`is`(updatedStartingDate))
        MatcherAssert.assertThat(result.endingDate, Matchers.`is`(updatedEndingDate))
    }

    @Test
    fun insertFeatureAndGetFeatureState() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val feature = Feature(name = "Feature", description = "Description", startingDate = startingDate, endingDate = endingDate, featureId = 1 ,projectId = 1, moduleId = 1)
        val task = Task(projectId = 1L, featureId = 1L)
        database.featureDao().insert(feature)
        database.taskDao().insert(task)

        //WHEN
        val result = database.featureDao().getFeatureStateById(1).getOrAwaitValue()

        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(result.feature.name, Matchers.`is`("Feature"))
        MatcherAssert.assertThat(result.feature.description, Matchers.`is`("Description"))
        MatcherAssert.assertThat(result.feature.startingDate, Matchers.`is`(startingDate))
        MatcherAssert.assertThat(result.feature.endingDate, Matchers.`is`(endingDate))
        MatcherAssert.assertThat(result.state, Matchers.`is`(State.IN_PROGRESS))
    }
}