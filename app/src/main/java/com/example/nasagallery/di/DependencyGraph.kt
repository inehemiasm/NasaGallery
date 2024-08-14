package com.example.nasagallery.di

import com.example.nasagallery.data.network.NasaImagesApiService
import com.example.nasagallery.data.repository.NasaImageRepository
import com.example.nasagallery.domain.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNasaImagesApiService(retrofit: Retrofit): NasaImagesApiService {
        return retrofit.create(NasaImagesApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNasaImagesRepository(apiService: NasaImagesApiService): NasaImageRepository {
        return NasaImageRepository(apiService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetItemsUseCase(nasaImageRepository: NasaImageRepository): GetItemsUseCase {
        return GetItemsUseCase(nasaImageRepository)
    }
}
