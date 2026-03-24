package com.example.nasa.domain.use.cases

import com.example.nasa.domain.NasaRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class IsFavoriteUseCase @Inject constructor(
    private val repository: NasaRepository
) {
    fun isFavorite (id: String): Flow<Boolean> {
        return repository.isFavorite(id)
    }
}
