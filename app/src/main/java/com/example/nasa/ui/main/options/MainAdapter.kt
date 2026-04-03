package com.example.nasa.ui.main.options


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa.R
import com.example.nasa.ui.main.SpaceClickListener
import com.example.nasa.ui.main.options.item.SpaceItem
import com.example.nasa.ui.main.options.item.SpaceItemViewHolder
import com.example.nasa.ui.main.options.item.big.card.BigCardViewHolder
import com.example.nasa.ui.main.options.item.big.card.BigHeaderItem
import com.example.nasa.ui.main.options.item.smal.card.SmallCardViewHolder
import com.example.nasa.ui.main.options.item.smal.card.SmallSectionItem

class MainAdapter(
    private val listener: SpaceClickListener
) : RecyclerView.Adapter<SpaceItemViewHolder>() {

    var items = emptyList<SpaceItem>()
        set(value) {
            val callback = SpaceDiffCallback(field, value)
            val result = DiffUtil.calculateDiff(callback)
            field = value
            result.dispatchUpdatesTo(this)
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

            SpaceItemType.SMALL -> SmallCardViewHolder(
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
        holder.bind(items[position])
    }

    override fun onBindViewHolder(
        holder: SpaceItemViewHolder,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(items[position], payload)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BigHeaderItem -> SpaceItemType.BIG
            is SmallSectionItem -> SpaceItemType.SMALL
            else -> error("хз in type")
        }
    }

    override fun getItemCount(): Int = items.size
}