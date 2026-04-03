package com.example.nasa.ui.main

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.ui.UISpaceData

object SpaceMapper {
    fun mapToUi(domain: SpacePhotoData): UISpaceData{
        return UISpaceData (
            nasaId = domain.nasaId,
            title =  domain.title,
            description = domain.description,
            imageUrl = domain.imageUrl,
            photographer = domain.photographer,
            location = domain.location,
            isFavorite = false,
            date = domain.data
        )
    }
}