package fr.im.salimi.projectmanager.ui.uiUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, U: ViewDataBinding>(
    private val listItems: List<T>,
    @LayoutRes private val layoutResource: Int
):RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T, U>>() {

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
        val item = listItems[position]
        holder.bindVH(item)
    }

    override fun getItemCount(): Int =
        listItems.size

    abstract fun bind(item: T, binding: U)

    abstract class BaseViewHolder<T, U: ViewDataBinding>(binding: U): RecyclerView.ViewHolder(binding.root) {

        abstract fun bindVH(item: T)
    }
}