package fr.im.salimi.projectmanager.ui.developerList

import fr.im.salimi.projectmanager.data.entities.Developer

class RecyclerClickListenersCallback(private val onLongClickCallback: (Long) -> Unit ) {
    fun onLongClick(developer: Developer) = onLongClickCallback(developer.id)
}