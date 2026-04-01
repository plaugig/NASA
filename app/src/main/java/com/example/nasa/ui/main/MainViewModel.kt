package com.example.nasa.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasa.domain.NasaInteractor
import com.example.nasa.ui.main.options.item.SpaceItem
import com.example.nasa.ui.main.options.item.big.card.BigHeaderItem
import com.example.nasa.ui.main.options.item.smal.card.SmolSectionItem
import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: NasaInteractor
) : ViewModel() {

    private val _screenItems = MutableLiveData<List<SpaceItem>>()
    val screenItem: LiveData<List<SpaceItem>> = _screenItems

    fun loadMainScreen() {
        viewModelScope.launch {
            val resultList = mutableListOf<SpaceItem>()

            val apodList = interactor.getSimplePhoto("APOD")
            apodList.firstOrNull()?.let { photoData ->
                val header = BigHeaderItem(
                    id = photoData.nasaId.hashCode(),
                    title = "Picture of the Day",
                    imageUrl = photoData.imageUrl,
                    query = "APOD"
                )
                resultList.add(header)

                resultList.add(
                    BigHeaderItem(
                        id = -1,
                        title = "Favorites",
                        imageUrl = null,
                        isFavorites = true
                    )
                )

                val sectionsConfig = mapOf(
                    "Mars Rovers" to listOf(
                        "Mars Curiosity",
                        "Mars Perseverance",
                        "Mars Spirit",
                        "Mars Opportunity",
                        "Mars InSight"
                    ),

                    "Moon Missions" to listOf(
                        "Apollo 11",
                        "Artemis",
                        "Lunar Surface",
                        "Moon Crater",
                        "Apollo 17"
                    ),

                    "The Sun" to listOf(
                        "Solar Orbiter",
                        "SOHO NASA",
                        "Solar Flare",
                        "Parker Solar Probe",
                        "Sun Eclipse"
                    ),

                    "Distant Galaxies" to listOf(
                        "Andromeda Galaxy",
                        "Milky Way",
                        "Hubble Deep Field",
                        "Sombrero Galaxy",
                        "Messier 87"
                    )
                )
                val deferredSections = sectionsConfig.map { (title, queries) ->
                    async { createSection(title, queries) }
                }

                resultList.addAll(deferredSections.awaitAll().filterNotNull())

                _screenItems.value = resultList

            }
        }
    }

    private suspend fun createSection(title: String, queries: List<String>): SmolSectionItem? {
        val photos = queries.mapNotNull { query ->
            val list = interactor.getSimplePhoto(query)

            list.firstOrNull()?.let { SpaceMapper.mapToUi(it) }
        }
        return if (photos.isNotEmpty()) SmolSectionItem(title, photos) else null
    }
}