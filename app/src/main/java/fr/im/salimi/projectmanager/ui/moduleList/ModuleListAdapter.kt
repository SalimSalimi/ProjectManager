package fr.im.salimi.projectmanager.ui.moduleList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.subsets.ModuleState
import fr.im.salimi.projectmanager.databinding.ModuleListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.progressDone
import java.util.*

class ModuleListAdapter(val listeners: ClickListenersCallback<Module>): BaseAdapter<ModuleState, ModuleListItemBinding>(R.layout.module_list_item) {

    override fun bind(item: ModuleState, binding: ModuleListItemBinding) {
        binding.module = item.module
        binding.listeners = listeners
        binding.state = item.state
        binding.moduleProgressbar.progressDone(item.module.startingDate.time, Date().time, item.module.endingDate.time)
    }
}