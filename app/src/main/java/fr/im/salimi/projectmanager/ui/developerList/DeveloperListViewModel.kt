package fr.im.salimi.projectmanager.ui.developerList

import androidx.lifecycle.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.helpers.Post
import fr.im.salimi.projectmanager.data.repositories.DeveloperRepository
import kotlinx.coroutines.launch


class DeveloperListViewModel(private val projectId: Long) : ViewModel() {

    private val _filter = MutableLiveData<Post>()
    val listDevelopers: LiveData<List<Developer>> = Transformations.switchMap(_filter) {
        if (it != Post.NONE) {
            if (projectId == -1L)
                DeveloperRepository.getDevelopersByPost(it)
            else
                DeveloperRepository.getDevelopersByPost(it)
        } else {
            DeveloperRepository.getAlll()
        }
    }

    private val _onAddBtnClickEvent = MutableLiveData<Boolean>()
    val onAddBtnClickEvent: LiveData<Boolean>
        get() = _onAddBtnClickEvent

    init {
        _filter.value = Post.NONE
        _onAddBtnClickEvent.value = false
    }

    fun deleteDeveloper(developer: Developer) {
        viewModelScope.launch {
            DeveloperRepository.delete(developer)
        }
    }

    fun onAddBtnClickedEvent() {
        _onAddBtnClickEvent.value = true
    }

    fun onAddBtnClickedEventDone() {
        _onAddBtnClickEvent.value = false
    }

    fun setFilter(post: Post) {
        this._filter.value = post
    }

}
