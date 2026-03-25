package com.example.nasa.ui.main.options.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SpaceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: SpaceItem)
}


