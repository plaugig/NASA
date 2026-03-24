package com.example.nasa.domain.use.cases

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.domain.NasaRepository
import jakarta.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: NasaRepository
) {
    suspend fun toggleFavorite(photo: SpacePhotoData) {
        repository.toggleFavorite(photo)
    }
}

