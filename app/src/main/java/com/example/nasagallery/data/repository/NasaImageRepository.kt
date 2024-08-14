package com.example.nasagallery.data.repository

import com.example.nasagallery.data.model.NasaImageUIModel
import com.example.nasagallery.data.network.NasaImagesApiService
import javax.inject.Inject

class NasaImageRepository @Inject constructor(
    private val apiService: NasaImagesApiService,
) {
    private var hasMore = true

    suspend fun searchImages(query: String, page: Int, pageSize: Int): Result<List<NasaImageUIModel>> {
        return try {
            val response = apiService.searchImages(query, page, pageSize)
            val images = response.collection.toUiImages()
            hasMore = images.size >= pageSize
            Result.success(images)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun hasMore(): Boolean = hasMore
}
