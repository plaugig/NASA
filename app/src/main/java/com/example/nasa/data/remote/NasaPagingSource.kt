package com.example.nasa.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasa.data.SpacePhotoData
import com.example.nasa.data.maper.toDomains
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NasaPagingSource(
    private val remote: NasaRemoteDataSource,
    private val query: String
) : PagingSource<Int, SpacePhotoData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpacePhotoData> {
        return withContext(Dispatchers.IO) {
            try {
                val curentPage = params.key ?: 1

                val response = remote.getSpacePhotos(query, curentPage)

                val photo = response.collection.items.map {
                    it.toDomains()
                }

                LoadResult.Page(
                    data = photo,
                    prevKey = if (curentPage == 1) null else curentPage - 1,
                    nextKey = if (photo.isEmpty()) null else curentPage + 1
                )

            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }


    }

    override fun getRefreshKey(state: PagingState<Int, SpacePhotoData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

