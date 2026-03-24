package com.example.nasa.domain

import androidx.paging.PagingData
import com.example.nasa.data.SpacePhotoData
import kotlinx.coroutines.flow.Flow

interface NasaRepository {
    suspend fun getSpacePhotos(query: String): Flow<PagingData<SpacePhotoData>>

    fun getLikePhotos(): Flow<List<SpacePhotoData>>

    suspend fun toggleFavorite(photo: SpacePhotoData)

    fun isFavorite(id: String): Flow<Boolean>



}