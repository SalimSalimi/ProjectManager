package fr.im.salimi.projectmanager.ui.taskForm

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import fr.im.salimi.projectmanager.data.repositories.TaskRepository
import kotlinx.coroutines.launch
import java.util.*

class TaskFormViewModel(private val id: Long, private val repository: TaskRepository,
                        developerRepository: DeveloperRepository) : ViewModel() {

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task>
        get() = _task

    private val _dateClickEvent = MutableLiveData<Boolean>()
    val dateClickEvent: LiveData<Boolean>
        get() = _dateClickEvent

    private val _addFabBtnClickEvent = MutableLiveData<Boolean>()
    val addFabBtnClickEvent: LiveData<Boolean>
        get() = _addFabBtnClickEvent

    private val _developersList = developerRepository.getAll()
    val developersList: LiveData<List<Developer>>
        get() = _developersList.asLiveData()

    private val developersSelectedId = mutableListOf<Long>()

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
        developersSelectedId.add(developer.id)
    }

    fun onRemoveDeveloper(developer: Developer) {
        developersSelectedId.remove(developer.id)
    }

    private fun initFunction() {
        if (id == -1L) {
            _task.value = Task()
            _task.value!!.functionId = 1L
        } else
            getTaskById()
    }

    private fun getTaskById() {
        viewModelScope.launch {
            _task.value = repository.getById(id)
            _task.value!!.functionId = 1L
        }
    }

    private fun insert() {
        viewModelScope.launch {
            _task.value!!.id = repository.insert(_task.value!!)
            insertAssignments()
        }
    }

    private fun update() {
        viewModelScope.launch {
            repository.update(_task.value!!)
            insertAssignments()
        }
    }

    private suspend fun insertAssignments() {
        val assignments = createTaskAssignments()
        if (!assignments.isNullOrEmpty()) {
            repository.insertTaskAssignments(assignments)
        }
    }

    private fun createTaskAssignments(): List<TaskAssignments> {
        val assignments = mutableListOf<TaskAssignments>()
        if (developersSelectedId.size > 0) {
            developersSelectedId.forEach { developerId ->
                assignments.add(TaskAssignments(developerId, _task.value!!.id))
            }
        }
        return assignments
    }
}