package com.example.nasa.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UISpaceData (
    val title: String,
    val description: String?,
    val photographer: String?,
    val location: String?,
    val nasaId: String,
    val imageUrl: String,
    val date: String,
    val isFavorite: Boolean = false
) : Parcelable