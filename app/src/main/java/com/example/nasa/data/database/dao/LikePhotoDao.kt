package com.example.nasa.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nasa.data.database.entity.LikePhotoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LikePhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(photo: LikePhotoEntity)

    @Delete
    suspend fun deleteFavorite(photo: LikePhotoEntity)

    @Query("SELECT * FROM like_photo")
    fun getAllFavorites(): Flow<List<LikePhotoEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM like_photo WHERE nasaId = :id)")
    suspend fun isFavorite(id: String): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM like_photo WHERE nasaId = :id)")
    fun isFavoriteFlow(id: String): Flow<Boolean>
}