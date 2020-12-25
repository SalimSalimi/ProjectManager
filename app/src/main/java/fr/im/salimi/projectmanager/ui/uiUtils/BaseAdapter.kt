package fr.im.salimi.projectmanager.ui.uiUtils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.im.salimi.projectmanager.data.entities.BaseEntity

abstract class BaseAdapter<T: BaseEntity, U: ViewDataBinding> (
    @LayoutRes private val layoutResource: Int
):ListAdapter<T, BaseAdapter.BaseViewHolder<T, U>>(BaseDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, U> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: U = DataBindingUtil.inflate(inflater, layoutResource, parent, false)

        return object : BaseViewHolder<T, U>(binding) {
            override fun bindVH(item: T) {
                bind(item, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, U>, position: Int) {
        val item = getItem(position)
        holder.bindVH(item)
    }

    abstract fun bind(item: T, binding: U)

    abstract class BaseViewHolder<T: BaseEntity, U: ViewDataBinding>(binding: U): RecyclerView.ViewHolder(binding.root) {
        abstract fun bindVH(item: T)
    }

    class BaseDiffUtilCallback<T: BaseEntity>: DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem == newItem
    }
}