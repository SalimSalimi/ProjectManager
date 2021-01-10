package fr.im.salimi.projectmanager.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import fr.im.salimi.projectmanager.data.database.ProjectRoomDatabase
import fr.im.salimi.projectmanager.data.entities.*
import fr.im.salimi.projectmanager.data.entities.Function
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

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class TaskAssignmentTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ProjectRoomDatabase

    //Dummy Data needed to avoid Foreign Key constraint error
    private fun initData() = runBlocking {
        database.projectDao().insert(Project())
        database.moduleDao().insert(Module(projectId = 1L))
        database.functionDao().insert(Function(moduleId = 1L))
    }

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                ProjectRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        initData()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun addTwoTasksToDeveloper_getTasksByDeveloper() = runBlocking {
        //Given
        val task1 = Task(name = "Task1", description = "Description1", functionId = 1L)
        val task2 = Task(name = "Task2", description = "Description2", functionId = 1L)

        val developer = Developer(firstName = "Salim", lastName = "Salimi")

        database.developerDao().insert(developer)
        database.taskDao().insert(task1)
        database.taskDao().insert(task2)

        //When
        val taskAssignment1 = TaskAssignments(1, 1)
        val taskAssignment2 = TaskAssignments(1, 2)
        database.taskDao().assignTask(taskAssignment1, taskAssignment2)
        //Then
        //Then
        val result = database.developerDao().getDeveloperWithTasksById(1)


        //Check Result
        MatcherAssert.assertThat(result, IsNull.notNullValue())

        //Check developer
        MatcherAssert.assertThat(result.developer, IsNull.notNullValue())
        MatcherAssert.assertThat(result.developer.firstName, Matchers.`is`("Salim"))
        MatcherAssert.assertThat(result.developer.lastName, Matchers.`is`("Salimi"))

        //Check task 1
        MatcherAssert.assertThat(result.tasks[0], IsNull.notNullValue())
        MatcherAssert.assertThat(result.tasks[0].taskId, Matchers.`is`(1L))
        MatcherAssert.assertThat(result.tasks[0].name, Matchers.`is`("Task1"))
        MatcherAssert.assertThat(result.tasks[0].description, Matchers.`is`("Description1"))

        // Check task 2
        MatcherAssert.assertThat(result.tasks[1], IsNull.notNullValue())
        MatcherAssert.assertThat(result.tasks[1].taskId, Matchers.`is`(2L))
        MatcherAssert.assertThat(result.tasks[1].name, Matchers.`is`("Task2"))
        MatcherAssert.assertThat(result.tasks[1].description, Matchers.`is`("Description2"))

    }

    @Test
    fun addTwoDevelopersToTask_getDevelopersByTask() = runBlocking {
        //Given
        val task1 = Task(name = "Task1", description = "Description1", functionId = 1L)

        val developer1 = Developer(firstName = "Salim", lastName = "Salimi")
        val developer2 = Developer(firstName = "Salim1", lastName = "Salimi1")

        database.developerDao().insert(developer1)
        database.developerDao().insert(developer2)
        database.taskDao().insert(task1)

        //When
        val taskAssignment1 = TaskAssignments(1, 1)
        val taskAssignment2 = TaskAssignments(2, 1)
        database.taskDao().assignTask(taskAssignment1, taskAssignment2)

        //Then
        val result = database.taskDao().getTaskWithDevelopersByTaskId(1)

        //Check Result
        MatcherAssert.assertThat(result, IsNull.notNullValue())

        //Check task
        MatcherAssert.assertThat(result.task, IsNull.notNullValue())
        MatcherAssert.assertThat(result.task.taskId, Matchers.`is`(1L))
        MatcherAssert.assertThat(result.task.name, Matchers.`is`("Task1"))
        MatcherAssert.assertThat(result.task.description, Matchers.`is`("Description1"))


        //Check task developer1

        MatcherAssert.assertThat(result.developers[0], IsNull.notNullValue())
        MatcherAssert.assertThat(result.developers[0].id, Matchers.`is`(1L))
        MatcherAssert.assertThat(result.developers[0].firstName, Matchers.`is`("Salim"))
        MatcherAssert.assertThat(result.developers[0].lastName, Matchers.`is`("Salimi"))

        // Check task developer2
        MatcherAssert.assertThat(result.developers[1], IsNull.notNullValue())
        MatcherAssert.assertThat(result.developers[1].id, Matchers.`is`(2L))
        MatcherAssert.assertThat(result.developers[1].firstName, Matchers.`is`("Salim1"))
        MatcherAssert.assertThat(result.developers[1].lastName, Matchers.`is`("Salimi1"))

    }
}