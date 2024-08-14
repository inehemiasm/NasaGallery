package com.example.nasagallery.data.network

import com.example.nasagallery.data.model.NasaImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY = "q"
private const val PAGE = "page"
private const val PAGE_SIZE = "page_size"

interface NasaImagesApiService {

    @GET("search")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): NasaImagesResponse
}
