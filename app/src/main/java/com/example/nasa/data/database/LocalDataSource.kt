package com.example.nasa.data.database

import com.example.nasa.data.database.dao.LikePhotoDao
import com.example.nasa.data.database.entity.LikePhotoEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class LocalDataSource @Inject constructor(
    private val appDataBase: AppDataBase
) {

    suspend fun insertFavorite(photo: LikePhotoEntity) {
        appDataBase.likePhotoDao.insertFavorite(photo)
    }

    suspend fun deleteFavorite(photo: LikePhotoEntity) {
        appDataBase.likePhotoDao.deleteFavorite(photo)
    }

    fun getAllFavorites(): Flow<List<LikePhotoEntity>> {
        return appDataBase.likePhotoDao.getAllFavorites()
    }

    suspend fun isFavorite(id: String): Boolean {
        return appDataBase.likePhotoDao.isFavorite(id)
    }

    fun isFavoriteFlow(id: String): Flow<Boolean> {
        return appDataBase.likePhotoDao.isFavoriteFlow(id)
    }
}
