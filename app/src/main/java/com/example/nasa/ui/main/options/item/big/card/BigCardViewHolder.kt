package com.example.nasa.ui.main.options.item.big.card

import android.view.View
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
        item as BigHeaderItem
        binding.title.text = item.title

        itemView.setOnClickListener {
            if (item.isFavorites) {
                listener.onFavoriteClick()
            } else {
                listener.onSearchQueryChange(item.query ?: "")
            }
        }
    }
}