package fr.im.salimi.projectmanager.ui.developerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.databinding.DeveloperListItemBinding
import fr.im.salimi.projectmanager.ui.developerList.DeveloperListAdapter.ViewHolder.Companion.create

class DeveloperListAdapter(private val clickListeners: RecyclerClickListenersCallback): RecyclerView.Adapter<DeveloperListAdapter.ViewHolder>() {

    var developers: List<Developer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDeveloper = developers[position]
        holder.bind(currentDeveloper, clickListeners)
    }

    override fun getItemCount() =
            developers.size

    class ViewHolder(private val binding: DeveloperListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(developer: Developer, clickListeners: RecyclerClickListenersCallback) {
            binding.developer = developer
            binding.developerCard.setOnLongClickListener {
                clickListeners.onLongClick(developer)
                true
            }
            binding.deleteDeveloperBtn.setOnClickListener {
                //clickListeners.onDeleteClick(developer)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding: DeveloperListItemBinding = DeveloperListItemBinding
                        .inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}