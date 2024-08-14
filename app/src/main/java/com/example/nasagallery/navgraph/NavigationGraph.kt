package com.example.nasagallery.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nasagallery.data.model.NasaImageUIModel
import com.example.nasagallery.presentation.NasaImagesViewModel
import com.example.nasagallery.ui.screen.NasaImageDetailBottomSheet
import com.example.nasagallery.ui.screen.SearchScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NasaImagesViewModel = hiltViewModel(),
) {
    var currentItem by remember {
        mutableStateOf<NasaImageUIModel?>(
            null,
        )
    }

    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            SearchScreen(
                modifier = modifier,
                viewModel = viewModel,
                onItemClicked = { item ->
                    currentItem = item
                    navController.navigate(DetailScreen)
                },
            )
        }
        composable<DetailScreen> { _ ->
            currentItem?.let {
                NasaImageDetailBottomSheet(
                    modifier = modifier,
                    nasaImageUIModel = it,
                    onDismiss = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
