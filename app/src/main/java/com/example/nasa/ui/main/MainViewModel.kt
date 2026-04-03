package com.example.nasa.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasa.domain.NasaInteractor
import com.example.nasa.ui.main.options.item.SpaceItem
import com.example.nasa.ui.main.options.item.big.card.BigHeaderItem
import com.example.nasa.ui.main.options.item.smal.card.SmallSectionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers


import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: NasaInteractor
) : ViewModel() {

    private val _screenItems = MutableLiveData<List<SpaceItem>>()
    val screenItem: LiveData<List<SpaceItem>> = _screenItems

    init {
        loadMainScreen()
    }

    private fun loadMainScreen() = viewModelScope.launch(Dispatchers.IO) {
        val resultList = mutableListOf<SpaceItem>()

        val favoriteCard = BigHeaderItem(
            id = -1,
            title = "Favorites",
            imageUrl = null,
            isFavorites = true,
            nasaId = "",
            description = ""
        )

        resultList.add(favoriteCard)
        _screenItems.value = resultList.toList()

        launch(Dispatchers.IO) {
            try {
                val artemisPhotos = interactor.getSimplePhoto("Artemis II mission")

                artemisPhotos.firstOrNull()?.let { firstPhoto ->
                    val header = BigHeaderItem(
                        id = firstPhoto.nasaId.hashCode(),
                        title = "Artemis II: Journey to the Moon",
                        imageUrl = firstPhoto.imageUrl,
                        query = "Artemis II",
                        nasaId = firstPhoto.nasaId,
                        description = firstPhoto.description ?: ""
                    )

                    withContext(Dispatchers.Main) {
                        resultList.add(0, header)
                        _screenItems.value = resultList.toList()
                    }
                }
            } catch (e: Exception) {
                android.util.Log.e("NASA_DEBUG", "Header error: ${e.message}")
            }
        }

        val sectionsConfig = mapOf(
            "Mars Exploration" to listOf(
                "Mars Surface",
                "Mars Rover",
                "Gale Crater",
                "Jezero Crater",
                "Mars Rocks"
            ),

            "Deep Space" to listOf(
                "James Webb: Discovery",
                "Galaxy Cluster",
                "Supernova",
                "Black Hole",
                "Hubble Heritage"
            ),

            "Solar System" to listOf(
                "Saturn Rings",
                "Jupiter Clouds",
                "Pluto Surface",
                "Solar Flare",
                "Earth from Space"
            ),


            "Distant Galaxies" to listOf(
                "Andromeda Galaxy",
                "Milky Way",
                "Hubble Deep Field",
                "Sombrero Galaxy",
                "Messier 87"
            )

        )

        sectionsConfig.forEach { (title, queries) ->
            launch(Dispatchers.IO) {
                try {
                    val section = createSection(title, queries)
                    if (section != null) {
                        withContext(Dispatchers.Main) {
                            resultList.add(section)
                            _screenItems.value = resultList.toList()
                        }
                    }
                } catch (e: Exception) {

                }
            }
        }
    }

    private suspend fun createSection(title: String, queries: List<String>): SmallSectionItem? {
        return coroutineScope {
            val deferredPhotos = queries.map { query ->
                async {
                    val list = interactor.getSimplePhoto(query)
                    list.firstOrNull()?.let {
                        SpaceMapper.mapToUi(it)
                    }
                }
            }

            val photos = deferredPhotos.awaitAll().filterNotNull()

            if (photos.isNotEmpty()) {
                SmallSectionItem(title, photos)
            } else {
                null
            }
        }
    }
}