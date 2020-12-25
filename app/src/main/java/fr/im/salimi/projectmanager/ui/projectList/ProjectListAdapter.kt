package fr.im.salimi.projectmanager.ui.projectList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.databinding.ProjectListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter


class ProjectListAdapter(
    projectsList: List<Project>,
    layoutResource: Int = R.layout.project_list_item): BaseAdapter<Project, ProjectListItemBinding>(projectsList, layoutResource) {

    override fun bind(item: Project, binding: ProjectListItemBinding) {
        binding.project = item
    }

}