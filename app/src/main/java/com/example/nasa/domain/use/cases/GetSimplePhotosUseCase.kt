package com.example.nasa.domain.use.cases

import com.example.nasa.data.SpacePhotoData
import com.example.nasa.domain.NasaRepository
import javax.inject.Inject

class GetSimplePhotosUseCase @Inject constructor(
    private val repository: NasaRepository
) {
    suspend fun getSimplePhotos(query: String): List<SpacePhotoData>{
        return repository.getSimplePhotos(query)
    }
}