package fr.im.salimi.projectmanager.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class ModuleDaoTest {

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
        }
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertModuleAndGetById() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val module = Module(name = "Module", description = "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module)

        //WHEN
        val result = database.moduleDao().getById(1)

        //Then
        assertThat(result, notNullValue())
        assertThat(result.name, `is`("Module"))
        assertThat(result.description, `is`("Description"))
        assertThat(result.startingDate, `is`(startingDate))
        assertThat(result.endingDate, `is`(endingDate))
    }

    @Test
    fun insertAndUpdateModuleAndGetById() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val module = Module(name = "Module", description = "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module)

        //WHEN
        val updatedStartingDate = Date()
        val updatedEndingDate = Date()
        val updatedModule = Module(id = 1, name = "Updated", description = "UpdatedDescription", updatedStartingDate, updatedEndingDate, projectId = 1)
        database.moduleDao().update(updatedModule)
        val result = database.moduleDao().getById(1)

        //Then
        assertThat(result, notNullValue())
        assertThat(result.name, `is`("Updated"))
        assertThat(result.description, `is`("UpdatedDescription"))
        assertThat(result.startingDate, `is`(updatedStartingDate))
        assertThat(result.endingDate, `is`(updatedEndingDate))
    }

    @Test
    fun insertModuleAndFunctions_getModuleByIdWithFunctions() = runBlocking {
        //Given
        val startingDate = Date()
        val endingDate = Date()
        val module = Module(name = "Module", description = "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module)

        val function1 = Feature(name = "function1", description = "description1", startingDate = startingDate, endingDate = endingDate,
                moduleId = 1L)

        val function2 = Feature(name = "function2", description = "description2", startingDate = startingDate, endingDate = endingDate,
                moduleId = 1L)

        database.featureDao().insert(function1)
        database.featureDao().insert(function2)

        //When
        val result = database.moduleDao().getByIdWithFeatures(1).asLiveData().getOrAwaitValue()

        val resModule = result.module
        val resFunctions = result.features
        //Then
        assertThat(result, notNullValue())
        assertThat(resModule, notNullValue())
        assertThat(resModule.name, `is`("Module"))
        assertThat(resModule.description, `is`("Description"))
        assertThat(resModule.startingDate, `is`(startingDate))
        assertThat(resModule.endingDate, `is`(endingDate))

        //function1
        assertThat(resFunctions, notNullValue())
        assertThat(resFunctions[0].name, `is`("function1"))
        assertThat(resFunctions[0].description, `is`("description1"))
        assertThat(resFunctions[0].startingDate, `is`(startingDate))
        assertThat(resFunctions[0].endingDate, `is`(endingDate))

        assertThat(resFunctions, notNullValue())
        assertThat(resFunctions[1].name, `is`("function2"))
        assertThat(resFunctions[1].description, `is`("description2"))
        assertThat(resFunctions[1].startingDate, `is`(startingDate))
        assertThat(resFunctions[1].endingDate, `is`(endingDate))
    }

    @Test
    fun insertModuleAndFunctions_getAllModulesWithFunctions() = runBlocking {
        //Given
        val startingDate = Date()
        val endingDate = Date()
        val module1 = Module(name = "Module", description = "Description", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module1)

        val module2 = Module(name = "Module1", description = "Description1", startingDate = startingDate, endingDate = endingDate, projectId = 1)
        database.moduleDao().insert(module2)


        val function1 = Feature(name = "function1", description = "description1", startingDate = startingDate, endingDate = endingDate,
                moduleId = 1L)

        val function2 = Feature(name = "function2", description = "description2", startingDate = startingDate, endingDate = endingDate,
                moduleId = 2L)

        database.featureDao().insert(function1)
        database.featureDao().insert(function2)

        //When
        val result = database.moduleDao().getAllWithFeatures().asLiveData().getOrAwaitValue()

        //Then
        val resModule = result[0].module
        val resFunctions = result[0].features

        assertThat(result, notNullValue())
        assertThat(resModule, notNullValue())
        assertThat(resModule.name, `is`("Module"))
        assertThat(resModule.description, `is`("Description"))
        assertThat(resModule.startingDate, `is`(startingDate))
        assertThat(resModule.endingDate, `is`(endingDate))

        //function1
        assertThat(resFunctions, notNullValue())
        assertThat(resFunctions[0].name, `is`("function1"))
        assertThat(resFunctions[0].description, `is`("description1"))
        assertThat(resFunctions[0].startingDate, `is`(startingDate))
        assertThat(resFunctions[0].endingDate, `is`(endingDate))

        val resModule1 = result[1].module
        val resFunctions1 = result[1].features

        assertThat(resModule1, notNullValue())
        assertThat(resModule1.name, `is`("Module1"))
        assertThat(resModule1.description, `is`("Description1"))
        assertThat(resModule1.startingDate, `is`(startingDate))
        assertThat(resModule1.endingDate, `is`(endingDate))


        assertThat(resFunctions1, notNullValue())
        assertThat(resFunctions1[0].name, `is`("function2"))
        assertThat(resFunctions1[0].description, `is`("description2"))
        assertThat(resFunctions1[0].startingDate, `is`(startingDate))
        assertThat(resFunctions1[0].endingDate, `is`(endingDate))
    }

}