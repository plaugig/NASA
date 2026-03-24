package com.example.nasa.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nasa.data.SpacePhotoData
import com.example.nasa.data.database.LocalDataSource
import com.example.nasa.data.maper.toDomain
import com.example.nasa.data.maper.toEntity
import com.example.nasa.data.remote.NasaPagingSource
import com.example.nasa.data.remote.NasaRemoteDataSource
import com.example.nasa.domain.NasaRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NasaRepositoryImpl @Inject constructor(
    private val remote: NasaRemoteDataSource,
    private val local: LocalDataSource
) : NasaRepository {
    override suspend fun getSpacePhotos(query: String): Flow<PagingData<SpacePhotoData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NasaPagingSource(remote, query)
            }
        ).flow

    }

    override fun getLikePhotos(): Flow<List<SpacePhotoData>> {
        return local.getAllFavorites().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun toggleFavorite(photo: SpacePhotoData) {
        val realyLiked = local.isFavorite(photo.nasaId)

        if (realyLiked){
            local.deleteFavorite(photo.toEntity())
        } else {
            local.insertFavorite(photo.toEntity())
        }
    }

    override fun isFavorite(id: String): Flow<Boolean> {
        return local.isFavoriteFlow(id)
    }

}