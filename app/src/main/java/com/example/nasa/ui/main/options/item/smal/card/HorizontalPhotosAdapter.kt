package com.example.nasa.ui.main.options.item.smal.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nasa.databinding.ItemHorizontalSmallCardBinding
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.main.SpaceClickListener
import com.example.nasa.ui.main.options.item.smal.card.item.HorizontalPhotosViewHolder

class HorizontalPhotosAdapter(
    private val listener: SpaceClickListener
) : ListAdapter<UISpaceData, HorizontalPhotosViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalPhotosViewHolder {
        val binding = ItemHorizontalSmallCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HorizontalPhotosViewHolder(binding.root, listener)

    }

    override fun onBindViewHolder(
        holder: HorizontalPhotosViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<UISpaceData>() {
        override fun areItemsTheSame(
            oldItem: UISpaceData,
            newItem: UISpaceData
        ): Boolean {
            return oldItem.nasaId == newItem.nasaId
        }

        override fun areContentsTheSame(
            oldItem: UISpaceData,
            newItem: UISpaceData
        ): Boolean {
            return oldItem == newItem
        }

    }


}