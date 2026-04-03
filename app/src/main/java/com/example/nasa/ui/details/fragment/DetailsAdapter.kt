package com.example.nasa.ui.details.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nasa.R
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.details.fragment.item.DetailsClickListener
import com.example.nasa.ui.details.fragment.item.DetailsViewHolder

class DetailsAdapter(
    private val listener: DetailsClickListener
) : PagingDataAdapter<UISpaceData, DetailsViewHolder>(DetailsDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detalis_card, parent, false)
        return DetailsViewHolder(view, listener)
    }

    override fun onBindViewHolder(
        holder: DetailsViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if (item != null){
            holder.bind(item)
        }

    }


    object DetailsDiffCallback : DiffUtil.ItemCallback<UISpaceData>() {
        override fun areItemsTheSame(oldItem: UISpaceData, newItem: UISpaceData): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: UISpaceData, newItem: UISpaceData): Boolean {
            return oldItem == newItem
        }
    }
}