package fr.im.salimi.projectmanager.ui.moduleList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.databinding.ModuleListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback

class ModuleListAdapter(val listeners: ClickListenersCallback<Module>): BaseAdapter<Module, ModuleListItemBinding>(R.layout.module_list_item) {

    override fun bind(item: Module, binding: ModuleListItemBinding) {
        binding.module = item
        binding.listeners = listeners
    }
}