package com.example.nasa.ui.main.options.item.big.card

data class BigHeaderItemPayload(
    val isIdChanged: Boolean,
    val isTitleChanged: Boolean,
    val isImageUrlChanged: Boolean,
    val isQueryChanged: Boolean,
    val isFavoritesChanged: Boolean,
    val isDescriptionChanged: Boolean
)