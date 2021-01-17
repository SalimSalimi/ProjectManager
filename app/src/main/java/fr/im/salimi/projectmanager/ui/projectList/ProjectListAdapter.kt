package fr.im.salimi.projectmanager.ui.projectList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.entities.subsets.ProjectState
import fr.im.salimi.projectmanager.databinding.ProjectListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.progressDone
import fr.im.salimi.projectmanager.ui.uiUtils.setBackgroundColorText
import java.util.*


class ProjectListAdapter(private val listeners: ClickListenersCallback<Project>): BaseAdapter<ProjectState, ProjectListItemBinding>(R.layout.project_list_item) {

    override fun bind(item: ProjectState, binding: ProjectListItemBinding) {
        val project = item.project
        binding.project = project
        binding.listener = listeners
        binding.state = item.state
        binding.projectLetterRounded.text = project.name[0].toString()
        binding.projectLetterRounded.setBackgroundColorText(project.name)
        binding.projectProgressbar.progressDone(project.startingDate.time, Date().time, project.deadline.time)
    }

}