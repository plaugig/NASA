package com.example.nasa.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("search")
    suspend fun searchPhoto(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("media_type") mediaType: String = "image",
       // @Query("api_key") apiKey: String = "A47NJ2WUaQteTmH1BZmbFWYA9m3mXAWaOBhdekoc"
    ): NasaResponseDto
}