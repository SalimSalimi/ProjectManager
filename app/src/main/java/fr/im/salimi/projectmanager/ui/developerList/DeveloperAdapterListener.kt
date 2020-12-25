package fr.im.salimi.projectmanager.ui.developerList

import android.view.View
import fr.im.salimi.projectmanager.data.entities.Developer

interface DeveloperAdapterListener {

    fun onLongClick(cardView: View, developer: Developer): Boolean
    fun onClick(cardView: View, developer: Developer)
}