package fr.im.salimi.projectmanager.ui.uiUtils

import android.view.View
import fr.im.salimi.projectmanager.data.entities.BaseEntity

interface ClickListenersCallback<T: BaseEntity> {

    fun onClick(view: View, entity: T)
    fun onLongClick(view: View, entity: T): Boolean
}