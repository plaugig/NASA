package com.example.nasa.ui.main.options.item.big.card

import android.view.View
import com.bumptech.glide.Glide
import com.example.nasa.R
import com.example.nasa.databinding.ItemMainBigCardBinding
import com.example.nasa.ui.main.SpaceClickListener
import com.example.nasa.ui.main.options.item.SpaceItem
import com.example.nasa.ui.main.options.item.SpaceItemViewHolder

class BigCardViewHolder(
    itemView: View,
    private val listener: SpaceClickListener

) : SpaceItemViewHolder(itemView) {

    private val binding = ItemMainBigCardBinding.bind(itemView)

    override fun bind(item: SpaceItem) {
        val headerItem = item as BigHeaderItem
        binding.title.text = item.title

        binding.title.text = if (headerItem.isFavorites) {
            itemView.context.getString(R.string.favorites)
        } else {
            headerItem.title
        }

        if (headerItem.isFavorites) {
            binding.favIcon.visibility = View.VISIBLE
            binding.image.setImageResource(R.drawable.favorites_card)

            binding.root.setOnClickListener {
                listener.onFavoriteClick()
            }
        } else {
            binding.favIcon.visibility = View.GONE

            Glide.with(binding.image)
                .load(headerItem.imageUrl)
                .placeholder(R.drawable.bg_glass)
                .centerCrop()
                .into(binding.image)

            binding.root.setOnClickListener {
                val searchQuery = headerItem.query ?: headerItem.title
                listener.onSearchClick(searchQuery)
            }
        }
    }

    override fun bind(item: SpaceItem, payload: Any?) {
        item as BigHeaderItem
        payload as BigHeaderItemPayload

        if (payload.isTitleChanged) {
            binding.title.text = if (item.isFavorites) {
                itemView.context.getString(R.string.favorites)
            } else {
                item.title
            }
        }
        if (payload.isFavoritesChanged) {
            binding.favIcon.visibility = if (item.isFavorites) {
                View.VISIBLE
            } else {
                View.GONE
            }

            if (item.isFavorites) {
                binding.image.setImageResource(R.drawable.favorites_card)

                binding.root.setOnClickListener {
                    listener.onFavoriteClick()
                }
            } else {
                Glide.with(binding.image)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.bg_glass)
                    .centerCrop()
                    .into(binding.image)

                binding.root.setOnClickListener {
                    val searchQuery = item.query ?: item.title
                    listener.onSearchClick(searchQuery)
                }
            }
        }
    }
}