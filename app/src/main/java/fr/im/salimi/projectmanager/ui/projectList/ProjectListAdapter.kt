package fr.im.salimi.projectmanager.ui.projectList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.databinding.ProjectListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback


class ProjectListAdapter(private val listeners: ClickListenersCallback<Project>,
    layoutResource: Int = R.layout.project_list_item): BaseAdapter<Project, ProjectListItemBinding>(layoutResource) {

    override fun bind(item: Project, binding: ProjectListItemBinding) {
        binding.project = item
        binding.listener = listeners
    }

}