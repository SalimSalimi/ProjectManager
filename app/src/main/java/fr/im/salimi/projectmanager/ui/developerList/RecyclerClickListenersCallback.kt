package fr.im.salimi.projectmanager.ui.developerList

import fr.im.salimi.projectmanager.data.entities.Developer

class RecyclerClickListenersCallback(
    private val onLongClickCallback: (Long) -> Unit,
    private val onDeleteClickCallback: (Developer) -> Unit) {

    fun onLongClick(developer: Developer) = onLongClickCallback(developer.developerId)
    fun onDeleteClick(developer: Developer) = onDeleteClickCallback(developer)
}