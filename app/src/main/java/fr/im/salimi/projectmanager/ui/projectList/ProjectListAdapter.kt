package fr.im.salimi.projectmanager.ui.projectList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.databinding.ProjectListItemBinding

class ProjectListAdapter(private val projectsList: List<Project>): RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectsList[position]
        holder.bind(project)
    }

    override fun getItemCount(): Int =
        projectsList.size

    class ViewHolder(private val binding: ProjectListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(project: Project) {
            binding.project = project
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ProjectListItemBinding = ProjectListItemBinding
                        .inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}