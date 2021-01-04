package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.Context
import android.widget.ArrayAdapter
import fr.im.salimi.projectmanager.data.entities.BaseEntity

class BaseSpinnerAdapter<T : BaseEntity>(context: Context, private val entitiesList: List<T>)
    : ArrayAdapter<T>(context, 0, entitiesList) {

    override fun getCount(): Int =
            entitiesList.size

}