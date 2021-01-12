package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.BaseEntity

abstract class BaseSpinnerAdapter<T : BaseEntity>(context: Context, private var entitiesList: List<T>)
    : ArrayAdapter<T>(context, R.layout.spinner_custom_base_layout, ArrayList(entitiesList)) {

    override fun getCount(): Int =
            entitiesList.size

    override fun getItem(position: Int): T? =
            entitiesList[position]

    override fun getItemId(position: Int): Long =
            entitiesList[position].id

    override fun getPosition(item: T?): Int =
            entitiesList.indexOf(item)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.spinner_custom_base_layout, parent, false)

        val titleTextView = view.findViewById<TextView>(R.id.entity_title_textview)
        val subTitleTextView = view.findViewById<TextView>(R.id.entity_subtitle_textview)
        val roundedLetter = view.findViewById<TextView>(R.id.entity_letter_rounded)

        val entity = getItem(position)

        onSetViews(entity!!, titleTextView, subTitleTextView, roundedLetter)

        return view
    }

    abstract fun onSetViews(item: T, titleView: TextView,
                            subTitleView: TextView, roundedLetter: TextView)

    open fun setEntitiesList(data: List<T>) {
        this.entitiesList = data
        this.notifyDataSetChanged()
    }

    fun getEntitiesList(): List<T> =
            this.entitiesList
}