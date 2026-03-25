package com.example.nasa.ui.main

import androidx.lifecycle.ViewModel
import com.example.nasa.domain.NasaInteractor
import jakarta.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: NasaInteractor
) : ViewModel() {
}