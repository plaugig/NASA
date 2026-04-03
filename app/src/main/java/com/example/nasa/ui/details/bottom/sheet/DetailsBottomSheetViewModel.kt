package com.example.nasa.ui.details.bottom.sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nasa.domain.NasaInteractor
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.details.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsBottomSheetViewModel @Inject constructor(
    private val interactor: NasaInteractor
): ViewModel(){

    fun toggleFavorite(photo: UISpaceData){
        viewModelScope.launch {
            interactor.toggleFavorite(photo.toDomain())
        }
    }

    fun isFavorite (id: String): LiveData<Boolean>{
      return  interactor.isFavorite(id).asLiveData()
    }

}