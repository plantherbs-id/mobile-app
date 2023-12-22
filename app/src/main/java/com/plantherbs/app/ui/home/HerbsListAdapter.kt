
package com.plantherbs.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plantherbs.app.data.model.DataItem
import com.plantherbs.app.databinding.HerbsItemBinding

class HerbsListAdapter :
    ListAdapter<DataItem, HerbsListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HerbsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val herbs = getItem(position)
        holder.bind(herbs)
    }

    class MyViewHolder(val binding: HerbsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(herbs: DataItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(herbs.image)
                    .into(image)

                name.text = herbs.title

                itemView.setOnClickListener {
                    // Handle item click event if needed
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
