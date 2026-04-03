package com.example.nasa.ui.main.options.item.smal.card.item

import android.view.View
import androidx.appcompat.resources.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasa.databinding.ItemHorizontalSmallCardBinding
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.main.SpaceClickListener

class HorizontalPhotosViewHolder(
    itemView: View,
    private val listener: SpaceClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemHorizontalSmallCardBinding.bind(itemView)

     fun bind(photo: UISpaceData) {

        binding.title.text = photo.title

        Glide.with(itemView.context)
            .load(photo.imageUrl)
            .centerCrop()
            .into(binding.image)

        itemView.setOnClickListener {
            listener.onSearchClick(photo.title)
        }
    }

}