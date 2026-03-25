package com.example.nasa.ui.main.options.item

abstract class SpaceItem {
    abstract fun isItemTheSame (item : SpaceItem) : Boolean
    abstract fun isContentTheSame (item : SpaceItem) : Boolean
}
