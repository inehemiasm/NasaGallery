package com.example.nasagallery.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nasagallery.R
import com.example.nasagallery.data.model.NasaImageUIModel
import com.example.nasagallery.presentation.ImagesUiState
import com.example.nasagallery.presentation.NasaImagesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: NasaImagesViewModel,
    onItemClicked: (NasaImageUIModel) -> Unit = {},
) {
    val pictureDetail by viewModel.images.collectAsState()
    var searchQuery by remember { mutableStateOf("Apollo") }
    LaunchedEffect(Unit) {
        viewModel.loadImages(query = searchQuery)
    }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(R.string.app_name))
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                )

                SearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    query = searchQuery,
                    onQueryChanged = { newQuery -> searchQuery = newQuery },
                    onSearch = {
                        viewModel.loadImages(query = searchQuery)
                    },
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            when (pictureDetail) {
                is ImagesUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
                is ImagesUiState.Success -> {
                    val images = (pictureDetail as ImagesUiState.Success).images
                    if (searchQuery.isNotBlank()) {
                        Text(
                            text = stringResource(R.string.search_results, searchQuery),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    GallerySection(
                        images,
                        onItemClicked,
                        onLoadMore = {
                            viewModel.loadImages(query = searchQuery)
                        },
                    )
                }
                is ImagesUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        val message = (pictureDetail as ImagesUiState.Error).message
                        Text(
                            text = message,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }
        }
    }
}
