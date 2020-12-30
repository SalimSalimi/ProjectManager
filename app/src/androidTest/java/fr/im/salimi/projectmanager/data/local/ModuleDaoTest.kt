package fr.im.salimi.projectmanager.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Module
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
        val module = Module(name = "Module", description =  "Description", startingDate = startingDate, endingDate = endingDate)
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
        val module = Module(name = "Module", description =  "Description", startingDate = startingDate, endingDate = endingDate)
        database.moduleDao().insert(module)

        //WHEN

        val updatedStartingDate = Date()
        val updatedEndingDate = Date()
        val updatedModule = Module(id = 1,name = "Updated", description = "UpdatedDescription", updatedStartingDate, updatedEndingDate)
        database.moduleDao().update(updatedModule)
        val result = database.moduleDao().getById(1)

        //Then
        assertThat(result, notNullValue())
        assertThat(result.name, `is`("Updated"))
        assertThat(result.description, `is`("UpdatedDescription"))
        assertThat(result.startingDate, `is`(updatedStartingDate))
        assertThat(result.endingDate, `is`(updatedEndingDate))
    }
}