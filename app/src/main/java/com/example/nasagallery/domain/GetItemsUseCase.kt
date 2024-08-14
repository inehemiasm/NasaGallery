package com.example.nasagallery.domain

import com.example.nasagallery.data.model.NasaImageUIModel
import com.example.nasagallery.data.repository.NasaImageRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: NasaImageRepository,
) {

    private var currentPage = 1
    private var hasMore = true
    private var currentCount = 0
    private val pageSize = 20

    suspend fun execute(query: String): Result<List<NasaImageUIModel>> {
        if (!hasMore) {
            return Result.success(emptyList())
        }

        val result = repository.searchImages(query, currentPage, pageSize)
        if (result.isSuccess) {
//            val images = result.getOrNull() ?: emptyList()
//            hasMore = images.size >= pageSize
            currentPage++
        }
        return result
    }

    fun resetPagination() {
//        currentPage = 1
        hasMore = repository.hasMore()
    }
}