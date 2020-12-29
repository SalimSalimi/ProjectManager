package fr.im.salimi.projectmanager.ui.developerList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.databinding.DeveloperListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback

class DeveloperListAdapter(private val clickListeners: ClickListenersCallback<Developer>): BaseAdapter<Developer, DeveloperListItemBinding>(R.layout.developer_list_item){

    override fun bind(item: Developer, binding: DeveloperListItemBinding) {
        binding.developer = item
        binding.listener = clickListeners
    }

}