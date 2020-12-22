package fr.im.salimi.projectmanager.ui

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(private val listItems: List<T>):RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return object : BaseViewHolder<T>(binding) {
            override fun bind(item: T) {
                bindVH(item, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = listItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int =
        listItems.size

    abstract fun bindVH(item: T, binding: ViewDataBinding)

    abstract class BaseViewHolder<T>(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: T)

    }
}