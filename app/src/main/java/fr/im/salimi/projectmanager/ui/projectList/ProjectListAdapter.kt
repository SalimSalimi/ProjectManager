package fr.im.salimi.projectmanager.ui.projectList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.databinding.ProjectListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.progressDone
import fr.im.salimi.projectmanager.ui.uiUtils.setBackgroundColorText
import java.util.*


class ProjectListAdapter(private val listeners: ClickListenersCallback<Project>) : BaseAdapter<Project, ProjectListItemBinding>(R.layout.project_list_item) {

    override fun bind(item: Project, binding: ProjectListItemBinding) {
        binding.project = item
        binding.listener = listeners
        //binding.state = item.state
        binding.projectLetterRounded.text = item.name[0].toString()
        binding.projectLetterRounded.setBackgroundColorText(item.name)
        binding.projectProgressbar.progressDone(item.startingDate.time, Date().time, item.deadline.time)

    }

}