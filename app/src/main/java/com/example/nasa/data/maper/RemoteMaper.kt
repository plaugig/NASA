package com.example.nasa.data.maper

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.data.remote.NasaItemDto

fun NasaItemDto.toDomain(): SpacePhotoData {
    val data = this.data.firstOrNull()
    return SpacePhotoData(
        nasaId = data?.nasaId ?: "",
        title = data?.title ?: "",
        description = data?.description ?: "",
        imageUrl = this.links?.firstOrNull()?.href ?: "",
        photographer = data?.photographer ?: "",
        location = data?.location ?: "",
    )
}