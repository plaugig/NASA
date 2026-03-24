package com.example.nasa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nasa.data.database.dao.LikePhotoDao
import com.example.nasa.data.database.entity.LikePhotoEntity


@Database(
    entities = [LikePhotoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract val likePhotoDao: LikePhotoDao
}