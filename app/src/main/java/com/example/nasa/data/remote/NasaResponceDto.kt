package com.example.nasa.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NasaResponseDto(
    val collection: NasaCollectionDto
)

@Serializable
data class NasaCollectionDto(
    val items: List<NasaItemDto>,
    val links: List<NasaPaginationDto>? = null
)

@Serializable
data class NasaItemDto(
    val data: List<NasaDataDto>,
    val links: List<NasaLinksDto>,
)

@Serializable
data class NasaDataDto(
    val title: String,
    val description: String? = null,
    val photographer: String? = null,
    val location: String? = null,
    @SerialName("nasa_id")
    val nasaId: String,
    @SerialName("media_type")
    val mediaType: String = "image",
)

@Serializable
data class NasaLinksDto(
    val href: String
)

@Serializable
data class NasaPaginationDto(
    val rel: String,
    val href: String,
)