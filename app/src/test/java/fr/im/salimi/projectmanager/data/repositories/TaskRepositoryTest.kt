package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.MainCoroutineRule
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.repositories.source.FakeTasksDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.core.IsNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskRepositoryTest {

    private lateinit var tasksList: MutableList<Task>
    private lateinit var dataSource: FakeTasksDataSource

    fun initRepository(): MutableList<Task> {
        val task1 = Task(taskId = 1, name = "task1", description = "description1")
        val task2 = Task(taskId = 2, name = "task2", description = "description2")
        val task3 = Task(taskId = 3, name = "task3", description = "description3")

        val list = mutableListOf<Task>()
        list.add(task1)
        list.add(task2)
        list.add(task3)
        return list
    }

    @get:Rule
    var mainCoroutine = MainCoroutineRule()

    @Before
    fun initData() {
        tasksList = initRepository()
        dataSource = FakeTasksDataSource(tasksList)
    }
    //Doesn't work with Flow

    @Test
    fun insertTask_getById() = mainCoroutine.runBlockingTest {
        val newTask = Task(taskId = 4, "Task4", "Description")

        dataSource.insert(newTask)

        val result = dataSource.getById(4L)
        assertThat(result, IsNull.notNullValue())
        assertThat(result.name, Matchers.`is`("Task4"))
        assertThat(result.description, Matchers.`is`("Description"))
    }

    @Test
    fun getByIdTask_deleteTask_getById() = mainCoroutine.runBlockingTest {
        val task = dataSource.getById(1)

        var result: Task? = null
        dataSource.delete(task)

        val exception = try {
            result = dataSource.getById(1L)
            false
        } catch (e: IndexOutOfBoundsException) {
            true
        }

        if (exception) {
            assertThat(exception, `is`(true))
        } else {
            assertThat(result, IsNull.nullValue())
            assertThat(result?.name, Matchers.isEmptyOrNullString())
            assertThat(result?.description, Matchers.isEmptyOrNullString())
        }
    }
}