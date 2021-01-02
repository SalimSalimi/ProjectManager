package fr.im.salimi.projectmanager.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.Task
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
class TaskDaoTest {
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
    fun insertTaskAndGetById() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val task = Task(name = "Task", description =  "Description", startingDate = startingDate, endingDate = endingDate)
        database.taskDao().insert(task)

        //WHEN
        val result = database.taskDao().getById(1)

        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(result.name, Matchers.`is`("Task"))
        MatcherAssert.assertThat(result.description, Matchers.`is`("Description"))
        MatcherAssert.assertThat(result.startingDate, Matchers.`is`(startingDate))
        MatcherAssert.assertThat(result.endingDate, Matchers.`is`(endingDate))
    }

    @Test
    fun insertAndUpdateModuleAndGetById() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val task = Task(name = "Task", description =  "Description", startingDate = startingDate, endingDate = endingDate)
        database.taskDao().insert(task)

        //WHEN

        val updatedStartingDate = Date()
        val updatedEndingDate = Date()
        val updatedTask = Task(taskId = 1,name = "TaskUpdated", description = "UpdatedDescription", updatedStartingDate, updatedEndingDate)
        database.taskDao().update(updatedTask)
        val result = database.taskDao().getById(1)

        //Then
        MatcherAssert.assertThat(result, IsNull.notNullValue())
        MatcherAssert.assertThat(result.name, Matchers.`is`("TaskUpdated"))
        MatcherAssert.assertThat(result.description, Matchers.`is`("UpdatedDescription"))
        MatcherAssert.assertThat(result.startingDate, Matchers.`is`(updatedStartingDate))
        MatcherAssert.assertThat(result.endingDate, Matchers.`is`(updatedEndingDate))
    }

    @Test
    fun insertAndGetByIdAndDelete() = runBlocking {
        // Given
        val startingDate = Date()
        val endingDate = Date()
        val task = Task(name = "Task", description =  "Description", startingDate = startingDate, endingDate = endingDate)
        database.taskDao().insert(task)

        //WHEN
        val result = database.taskDao().getById(1)
        database.taskDao().delete(result)

        //Then
        val deletedResult = database.taskDao().getById(1)
        MatcherAssert.assertThat(deletedResult, IsNull.nullValue())
    }
}