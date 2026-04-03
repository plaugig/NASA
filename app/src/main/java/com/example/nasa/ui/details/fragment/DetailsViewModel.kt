package com.example.nasa.ui.details.fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.nasa.domain.NasaInteractor
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.details.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val interactor: NasaInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val query = savedStateHandle.get<String>("QUERY").orEmpty()
    private val isFav = savedStateHandle.get<Boolean>("IS_FAV") ?: false


    suspend fun getPhoto(): Flow<PagingData<UISpaceData>> {
        return if (isFav) {
            interactor.getLikePhotos().map { list ->
                PagingData.from(list.map { it.toUi() })
            }
        } else {
            interactor.getSpacePhotos(query).map { pagingData ->
                pagingData.map { it.toUi() }
            }
        }.cachedIn(viewModelScope)
    }
}