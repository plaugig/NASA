package com.example.nasa.data.maper

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.data.database.entity.LikePhotoEntity

fun LikePhotoEntity.toDomain(): SpacePhotoData {
return SpacePhotoData (
    nasaId = this.nasaId,
    title = this.title,
    description = this.description,
    imageUrl = this.url,
    photographer = this.photographer,
    location = this.location,
    data = this.data
)
}

fun SpacePhotoData.toEntity(): LikePhotoEntity {
    return LikePhotoEntity (
        nasaId = this.nasaId,
        title = this.title,
        description = this.description,
        url = this.imageUrl,
        photographer = this.photographer,
        location = this.location,
        data = this.data
    )
}