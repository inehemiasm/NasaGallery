package com.example.nasagallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.nasagallery.navgraph.NavigationGraph
import com.example.nasagallery.presentation.NasaImagesViewModel
import com.example.nasagallery.ui.theme.NasaGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NasaGalleryTheme {
                val viewModel: NasaImagesViewModel = hiltViewModel()
                val navController = rememberNavController()
                NavigationGraph(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }
}
