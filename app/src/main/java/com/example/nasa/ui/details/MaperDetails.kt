package com.example.nasa.ui.details

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.ui.UISpaceData

fun SpacePhotoData.toUi(): UISpaceData {
    return UISpaceData(
        nasaId = this.nasaId,
        imageUrl = this.imageUrl,
        title = this.title,
        description = this.description,
        photographer = this.photographer,
        location = this.location,
        date = this.data

    )

}

fun UISpaceData.toDomain(): SpacePhotoData {
    return SpacePhotoData(
        nasaId = this.nasaId,
        imageUrl = this.imageUrl,
        title = this.title,
        description = this.description,
        photographer = this.photographer,
        location = this.location,
        data = this.date
    )
}