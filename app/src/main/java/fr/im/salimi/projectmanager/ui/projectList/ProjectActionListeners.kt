package fr.im.salimi.projectmanager.ui.projectList

import android.view.View
import fr.im.salimi.projectmanager.data.entities.Project

interface ProjectActionListeners {

    fun onClickListener(view: View, project: Project)
    fun onLongClickListener(view: View, project: Project): Boolean
}