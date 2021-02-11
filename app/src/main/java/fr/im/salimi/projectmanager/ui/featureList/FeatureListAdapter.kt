package fr.im.salimi.projectmanager.ui.featureList

import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.databinding.FeatureListItemBinding
import fr.im.salimi.projectmanager.ui.uiUtils.BaseAdapter
import fr.im.salimi.projectmanager.ui.uiUtils.ClickListenersCallback
import fr.im.salimi.projectmanager.ui.uiUtils.progressDone
import java.util.*

class FeatureListAdapter(private val listeners: ClickListenersCallback<Feature>):BaseAdapter<Feature, FeatureListItemBinding>(R.layout.feature_list_item) {
    override fun bind(item: Feature, binding: FeatureListItemBinding) {

        binding.feature = item
        binding.listeners = listeners
        //binding.state = item.state
        binding.featureProgressbar.progressDone(item.startingDate.time, Date().time, item.endingDate.time)
    }

}