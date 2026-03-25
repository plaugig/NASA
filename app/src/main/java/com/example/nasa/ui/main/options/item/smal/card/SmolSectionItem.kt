package com.example.nasa.ui.main.options.item.smal.card

import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.main.options.item.SpaceItem

data class SmolSectionItem(
    val title: String,
    val photos: List<UISpaceData>
) : SpaceItem() {
    override fun isItemTheSame(item: SpaceItem): Boolean {
       return item is SmolSectionItem && item.title == title
    }

    override fun isContentTheSame(item: SpaceItem): Boolean {
        return item is SmolSectionItem && photos == item.photos
    }
}



