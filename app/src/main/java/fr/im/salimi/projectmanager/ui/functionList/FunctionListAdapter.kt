package fr.im.salimi.projectmanager.ui.functionList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.databinding.FunctionListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback

class FunctionListAdapter(private val listeners: ClickListenersCallback<Function>):BaseAdapter<Function, FunctionListItemBinding>(R.layout.function_list_item) {
    override fun bind(item: Function, binding: FunctionListItemBinding) {
        binding.function = item
        binding.listeners = listeners
    }
}