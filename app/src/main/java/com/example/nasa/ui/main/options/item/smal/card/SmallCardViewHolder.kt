package com.example.nasa.ui.main.options.item.smal.card

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa.databinding.ItemMainHorizontalSectionBinding
import com.example.nasa.ui.main.SpaceClickListener
import com.example.nasa.ui.main.options.item.SpaceItem
import com.example.nasa.ui.main.options.item.SpaceItemViewHolder

class SmallCardViewHolder(
    itemView: View,
    listener: SpaceClickListener
) : SpaceItemViewHolder(itemView) {

    private val binding = ItemMainHorizontalSectionBinding.bind(itemView)

    private val horizontalAdapter = HorizontalPhotosAdapter(listener)

    init {
        binding.horizontalRecycler.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = horizontalAdapter
        }
    }

    override fun bind(item: SpaceItem) {
        item as SmallSectionItem

        binding.sectionTitle.text = item.title

        horizontalAdapter.submitList(item.photos)
    }
}