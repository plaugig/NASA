package com.example.nasa.domain.use.cases

import androidx.paging.PagingData
import com.example.nasa.data.SpacePhotoData
import com.example.nasa.domain.NasaRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSpacePhotoUseCase @Inject constructor(
    private val repository: NasaRepository
) {
    suspend fun getSpacePhotos (query: String): Flow<PagingData<SpacePhotoData>> {
        return repository.getSpacePhotos(query)
    }
}