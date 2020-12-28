package fr.im.salimi.projectmanager.ui.moduleList

import androidx.annotation.LayoutRes
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.databinding.ModuleListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter

class ModuleListAdapter(@LayoutRes layoutRes: Int = R.layout.module_list_item): BaseAdapter<Module, ModuleListItemBinding>(layoutRes) {
    override fun bind(item: Module, binding: ModuleListItemBinding) {
        binding.module = item
    }
}