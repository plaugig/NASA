package com.example.nasa.domain

import androidx.paging.PagingData
import com.example.nasa.data.SpacePhotoData
import com.example.nasa.domain.use.cases.GetLikePhotoUserCase
import com.example.nasa.domain.use.cases.GetSpacePhotoUseCase
import com.example.nasa.domain.use.cases.IsFavoriteUseCase
import com.example.nasa.domain.use.cases.ToggleFavoriteUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class NasaInteractor @Inject constructor(
    private val getLikePhotoUserCase: GetLikePhotoUserCase,
    private val getSpacePhotoUseCase: GetSpacePhotoUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) {
    fun getLikePhotos(): Flow<List<SpacePhotoData>> {
        return getLikePhotoUserCase.getLikePhotos()
    }

    suspend fun getSpacePhotos(query: String): Flow<PagingData<SpacePhotoData>> {
        return getSpacePhotoUseCase.getSpacePhotos(query)

    }

    suspend fun toggleFavorite(photo: SpacePhotoData) {
        toggleFavoriteUseCase.toggleFavorite(photo)
    }

    fun isFavorite(id: String): Flow<Boolean> {
        return isFavoriteUseCase.isFavorite(id)
    }
}
