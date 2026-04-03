package com.example.nasa.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_photo")
data class LikePhotoEntity (
    @PrimaryKey
    val nasaId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "photographer")
    val photographer: String?,

    @ColumnInfo(name = "location")
    val location: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "data")
    val data: String

)