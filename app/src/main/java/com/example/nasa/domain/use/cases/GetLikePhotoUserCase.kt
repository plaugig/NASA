package com.example.nasa.domain.use.cases

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.domain.NasaRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLikePhotoUserCase @Inject constructor(
    private val repository: NasaRepository
) {
    fun getLikePhotos(): Flow<List<SpacePhotoData>> {
        return repository.getLikePhotos()
    }
}