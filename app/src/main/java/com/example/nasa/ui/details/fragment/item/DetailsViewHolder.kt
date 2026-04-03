package com.example.nasa.ui.details.fragment.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasa.databinding.ItemDetalisCardBinding
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.details.fragment.item.DetailsClickListener

class DetailsViewHolder(
    itemView: View,
    private val listener: DetailsClickListener
): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemDetalisCardBinding.bind(itemView)

    fun bind(photo: UISpaceData) {

        Glide.with(itemView.context)
            .load(photo.imageUrl)
            .centerCrop()
            .into(binding.image)

        binding.root.setOnClickListener {
            listener.onDetailClick(photo)
        }
    }
}