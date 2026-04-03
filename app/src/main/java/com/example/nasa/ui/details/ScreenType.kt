package com.example.nasa.ui.details

sealed class ScreenType {
    data class RemoteSearch(val query : String): ScreenType()
    object Favorites: ScreenType()
}