package com.example.nasa.ui.details.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.nasa.domain.NasaInteractor
import com.example.nasa.ui.UISpaceData
import com.example.nasa.ui.details.ScreenType
import com.example.nasa.ui.details.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val interactor: NasaInteractor
) : ViewModel() {


    suspend fun getPhoto(screenType: ScreenType): Flow<PagingData<UISpaceData>> {
        return when (screenType) {
            is ScreenType.RemoteSearch -> {
                interactor.getSpacePhotos(screenType.query)
                    .map { pagingData ->
                        pagingData.map { it.toUi() }
                    }
            }

            is ScreenType.Favorites -> {
                interactor.getLikePhotos()
                    .map { list ->
                        PagingData.from(list.map { it.toUi() })
                    }
            }
        }.cachedIn(viewModelScope)
    }


}