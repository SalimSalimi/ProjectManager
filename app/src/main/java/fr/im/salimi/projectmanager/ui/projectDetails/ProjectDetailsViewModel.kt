package fr.im.salimi.projectmanager.ui.projectDetails

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.helpers.Post
import fr.im.salimi.projectmanager.data.helpers.State
import fr.im.salimi.projectmanager.data.repositories.*
import kotlinx.coroutines.launch

class ProjectDetailsViewModel(private val id: Long) : ViewModel() {

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    private val _featureNumberState = FeatureRepository.getNumberStateByProjectId(id)
    val featureNumberState: LiveData<Map<State?, Int?>> = Transformations.map(_featureNumberState) { list ->
        val map = LinkedHashMap<State?, Int?>()
        list.forEach { item ->
            map[item.state] = item.number
        }
        map
    }

    private val _tasksNumberState = TaskRepository.getNumberStateByProjectId(id)
    val tasksNumberState: LiveData<Map<State?, Int?>> = Transformations.map(_tasksNumberState) { list ->
        val map = LinkedHashMap<State?, Int?>()
        list.forEach { item ->
            map[item.state] = item.number
        }

        map
    }

    private val _modulesNumberState = ModuleRepository.getNumberStateByProjectId(id)
    val modulesNumberState: LiveData<Map<State?, Int?>> = Transformations.map(_modulesNumberState) { list ->
        val map = LinkedHashMap<State?, Int?>()
        list.forEach { item ->
            map[item.state] = item.number
        }
        map
    }

    private val _developersNumberPost = DeveloperRepository.getNumberDevelopersByPostByProjectId(id)
    val developersNumberPost: LiveData<Map<Post?, Int?>> = Transformations.map(_developersNumberPost) { list ->
        val map = LinkedHashMap<Post?, Int?>()
        list.forEach { item ->
            if (item.post != Post.NONE)
                map[item.post] = item.number
        }
        map
    }

    private val _getProjectIsDone = MutableLiveData<Boolean>()
    val getProjectIsDone: LiveData<Boolean>
        get() = _getProjectIsDone

    private val _modulesClickEvent = MutableLiveData<Boolean>()
    val modulesClickEvent: LiveData<Boolean>
        get() = _modulesClickEvent

    private val _featuresClickEvent = MutableLiveData<Boolean>()
    val featuresClickEvent: LiveData<Boolean>
        get() = _featuresClickEvent

    private val _tasksClickEvent = MutableLiveData<Boolean>()
    val tasksClickEvent: LiveData<Boolean>
        get() = _tasksClickEvent

    private val _developersClickEvent = MutableLiveData<Boolean>()
    val developersClickEvent: LiveData<Boolean>
        get() = _developersClickEvent


    init {
        initProject()
        initEvents()
    }

    fun onGetProjectDone() {
        _getProjectIsDone.value = false
    }

    fun onModulesClick() {
        _modulesClickEvent.value = true
    }

    fun onModulesClicked() {
        _modulesClickEvent.value = false
    }

    fun onFeaturesClick() {
        _featuresClickEvent.value = true
    }

    fun onFeaturesClicked() {
        _featuresClickEvent.value = false
    }

    fun onTasksClick() {
        _tasksClickEvent.value = true
    }

    fun onTasksClicked() {
        _tasksClickEvent.value = false
    }

    fun onDevelopersClick() {
        _developersClickEvent.value = true
    }

    fun onDevelopersClicked() {
        _developersClickEvent.value = false
    }

    private fun initProject() {
        viewModelScope.launch {
            _project.value = ProjectRepository.getById(id)
        }
    }

    private fun initEvents() {

        _getProjectIsDone.value = false
        _modulesClickEvent.value = false
        _featuresClickEvent.value = false
        _tasksClickEvent.value = false
        _developersClickEvent.value = false
    }
}