package com.example.nasa.data.remote

import javax.inject.Inject

class NasaRemoteDataSource @Inject constructor(
    private val api : NasaApi
) {
    suspend fun getSpacePhotos(query: String, page: Int ): NasaResponseDto {
        return api.searchPhoto(query, page)
    }
}