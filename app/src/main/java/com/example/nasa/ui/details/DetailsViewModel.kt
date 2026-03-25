package com.example.nasa.ui.details

import androidx.lifecycle.ViewModel
import com.example.nasa.domain.NasaInteractor
import com.example.nasa.domain.NasaRepository
import jakarta.inject.Inject

class DetailsViewModel @Inject constructor(
    private val interactor: NasaInteractor
): ViewModel() {
}