package com.example.nasagallery.presentation

import com.example.nasagallery.data.model.NasaImageUIModel

sealed class ImagesUiState {
    object Loading : ImagesUiState()
    data class Success(val images: List<NasaImageUIModel>) : ImagesUiState()
    data class Error(val message: String) : ImagesUiState()
}
