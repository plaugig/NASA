package com.example.nasa.ui.main.options.item.big.card

import com.example.nasa.ui.main.options.item.SpaceItem

data class BigHeaderItem (
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val query: String? = null,
    val isFavorites: Boolean = false
) : SpaceItem() {

    override fun isItemTheSame(item: SpaceItem): Boolean {
       return item is BigHeaderItem && this.id == item.id
    }

    override fun isContentTheSame(item: SpaceItem): Boolean {
        return item is BigHeaderItem && this == item

    }
}