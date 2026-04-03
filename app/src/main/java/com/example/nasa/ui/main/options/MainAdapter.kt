package com.example.nasa.ui.main.options


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nasa.R
import com.example.nasa.ui.main.SpaceClickListener
import com.example.nasa.ui.main.options.item.SpaceItem
import com.example.nasa.ui.main.options.item.SpaceItemViewHolder
import com.example.nasa.ui.main.options.item.big.card.BigCardViewHolder
import com.example.nasa.ui.main.options.item.big.card.BigHeaderItem
import com.example.nasa.ui.main.options.item.smal.card.SmolCardViewHolder
import com.example.nasa.ui.main.options.item.smal.card.SmallSectionItem

class MainAdapter(
    private val listener: SpaceClickListener
) : ListAdapter<SpaceItem, SpaceItemViewHolder>(SpaceDiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BigHeaderItem -> SpaceItemType.BIG
            is SmallSectionItem -> SpaceItemType.SMOL
            else -> error("хз in type")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpaceItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SpaceItemType.BIG -> BigCardViewHolder(
                inflater.inflate(
                    R.layout.item_main_big_card,
                    parent,
                    false
                ),
                listener
            )

            SpaceItemType.SMOL -> SmolCardViewHolder(
                inflater.inflate(
                    R.layout.item_main_horizontal_section,
                    parent,
                    false
                ),
                listener
            )

            else -> error("ivalid you and type")

        }
    }

    override fun onBindViewHolder(
        holder: SpaceItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    object SpaceDiffCallback : DiffUtil.ItemCallback<SpaceItem>() {
        override fun areItemsTheSame(
            oldItem: SpaceItem,
            newItem: SpaceItem
        ): Boolean {
            return oldItem.isItemTheSame(newItem)
        }

        override fun areContentsTheSame(
            oldItem: SpaceItem,
            newItem: SpaceItem
        ): Boolean {
            return oldItem.isContentTheSame(newItem)
        }

    }

}