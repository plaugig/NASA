package com.example.nasa.ui

data class UISpaceData (
    val title: String,
    val description: String?,
    val photographer: String?,
    val location: String?,
    val nasaId: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)