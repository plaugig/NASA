package com.example.nasa.ui.main.options.item.big.card

import com.example.nasa.ui.main.options.item.SpaceItem
import kotlin.Boolean

data class BigHeaderItem(
    val id: String,
    val title: String,
    val imageUrl: String?,
    val query: String? = null,
    val isFavorites: Boolean = false,
    val description: String? = null
) : SpaceItem() {

    override fun isItemTheSame(item: SpaceItem): Boolean {
        return item is BigHeaderItem && this.id == item.id
    }

    override fun isContentTheSame(item: SpaceItem): Boolean {
        item as BigHeaderItem

        return title == item.title &&
                imageUrl == item.imageUrl &&
                query == item.query &&
                isFavorites == item.isFavorites &&
                description == item.description
    }

    override fun getPayload(item: SpaceItem): Any {
        item as BigHeaderItem

        return BigHeaderItemPayload(
            isIdChanged = id != item.id,
            isTitleChanged = title != item.title,
            isImageUrlChanged = imageUrl != item.imageUrl,
            isQueryChanged = query != item.query,
            isFavoritesChanged = isFavorites != item.isFavorites,
            isDescriptionChanged = description != item.description
        )
    }
}