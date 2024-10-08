package com.example.nasagallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasagallery.domain.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NasaImagesViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _images = MutableStateFlow<ImagesUiState>(ImagesUiState.Loading)
    val images: StateFlow<ImagesUiState> = _images

    private var currentQuery: String = ""

    fun loadImages(query: String) {
        if (query != currentQuery) {
            currentQuery = query
            getItemsUseCase.resetPagination()
            _images.value = ImagesUiState.Loading
        }

        viewModelScope.launch(Dispatchers.IO) {
            val result = getItemsUseCase.execute(query)
            if (result.isSuccess) {
                val newImages = result.getOrNull() ?: emptyList()
                val existingImages = when (_images.value) {
                    is ImagesUiState.Success -> (_images.value as ImagesUiState.Success).images
                    else -> emptyList()
                }

                _images.value = if (query == currentQuery) {
                    ImagesUiState.Success(existingImages + newImages)
                } else {
                    ImagesUiState.Success(newImages)
                }
            } else {
                _images.value = ImagesUiState.Error("Oops.. Something went wrong!!!")
            }
        }
    }
}
