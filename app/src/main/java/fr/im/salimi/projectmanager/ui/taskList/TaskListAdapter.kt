package fr.im.salimi.projectmanager.ui.taskList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.databinding.TaskListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback

class TaskListAdapter(private val listeners: ClickListenersCallback<Task>): BaseAdapter<Task, TaskListItemBinding>(R.layout.task_list_item){
    override fun bind(item: Task, binding: TaskListItemBinding) {
        binding.task = item
        binding.listeners = listeners
    }
}