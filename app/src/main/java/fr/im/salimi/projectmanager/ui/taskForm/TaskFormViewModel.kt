package fr.im.salimi.projectmanager.ui.taskForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class TaskFormViewModel(private val id: Long) : ViewModel() {

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task>
        get() = _task

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addFabBtnClickEvent = MutableLiveData<Boolean>()
    val addFabBtnClickEvent: LiveData<Boolean>
        get() = _addFabBtnClickEvent

    private val _developersList = DeveloperRepository.getAll()
    val developersList: LiveData<List<Developer>>
        get() = _developersList

    private val mutableAssignedDevelopersList = ArrayList<Developer>()

    private val _assignedDevelopers = MutableLiveData<List<Developer>>()
    val assignedDevelopers: LiveData<List<Developer>>
        get() = _assignedDevelopers

    init {
        initFunction()
        _addFabBtnClickEvent.value = false
        _dateClickEvent.value = false
    }

    fun onDateClickedEvent() {
        _dateClickEvent.value = true
    }

    fun onDateClickedEventFinished() {
        _dateClickEvent.value = false
    }

    fun onAddFabBtnClickEvent() {
        _addFabBtnClickEvent.value = true
    }

    fun onAddFabClickedEventFinished() {
        _addFabBtnClickEvent.value = false
    }

    fun upsert() {
        if (id == -1L)
            insert()
        else
            update()
    }

    fun onChooseDate(startingDate: Date, endingDate: Date) {
        _task.value?.startingDate = startingDate
        _task.value?.endingDate = endingDate
    }

    fun onChooseDeveloper(developer: Developer) {
        mutableAssignedDevelopersList.add(developer)
        _assignedDevelopers.value = mutableAssignedDevelopersList
    }

    fun onRemoveDeveloper(developer: Developer) {
        mutableAssignedDevelopersList.remove(developer)
        _assignedDevelopers.value = mutableAssignedDevelopersList
    }

    private fun initFunction() {
        if (id == -1L) {
            _task.value = Task()
            //TODO Remove it after when implementing function choose
            _task.value!!.featureId = 1L
            _task.value!!.projectId = 1L
        } else
            getTaskById()
    }

    private fun getTaskById() {
        viewModelScope.launch {
            val taskWithAssignments = TaskRepository.getTaskAssignmentsByTaskId(id)
            _task.value = taskWithAssignments.task
            //TODO Remove it after when implementing function choose
            _task.value!!.featureId = 1L
            mutableAssignedDevelopersList.addAll(taskWithAssignments.developers)
            _assignedDevelopers.value = mutableAssignedDevelopersList
        }
    }

    private fun insert() {
        viewModelScope.launch {
            _task.value!!.id = TaskRepository.insert(_task.value!!)
            insertAssignments()
        }
    }

    private fun update() {
        viewModelScope.launch {
            TaskRepository.update(_task.value!!)
            insertAssignments()
        }
    }

    private suspend fun insertAssignments() {
        val assignments = createTaskAssignments()
        if (!assignments.isNullOrEmpty()) {
            TaskRepository.insertTaskAssignments(assignments)
        }
    }

    private fun createTaskAssignments(): List<TaskAssignments> {
        val assignments = mutableListOf<TaskAssignments>()
        if (_assignedDevelopers.value?.isNotEmpty() == true) {
            _assignedDevelopers.value?.forEach { developer ->
                assignments.add(TaskAssignments(developer.id, _task.value!!.id))
            }
        }
        return assignments
    }
}