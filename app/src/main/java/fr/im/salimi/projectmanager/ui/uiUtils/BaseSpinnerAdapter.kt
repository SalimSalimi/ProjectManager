package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.BaseEntity

abstract class BaseSpinnerAdapter<T : BaseEntity>(context: Context, private val entitiesList: List<T>)
    : ArrayAdapter<T>(context, R.layout.spinner_custom_base_layout, entitiesList) {

    override fun getCount(): Int =
            entitiesList.size


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_custom_base_layout, parent, false)

        val titleTextView = view.findViewById<TextView>(R.id.entity_title_textview)
        val subTitleTextView = view.findViewById<TextView>(R.id.entity_subtitle_textview)
        val roundedLetter = view.findViewById<TextView>(R.id.entity_letter_rounded)

        val entity = getItem(position)

        onSetViews(entity!!, titleTextView, subTitleTextView, roundedLetter)

        return view
    }

    abstract fun onSetViews(item: T, titleView: TextView,
                            subTitleView: TextView, roundedLetter: TextView)
}