package com.example.nasagallery.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nasagallery.data.model.NasaImageUIModel

@Composable
fun GallerySection(
    images: List<NasaImageUIModel>,
    onItemClicked: (NasaImageUIModel) -> Unit,
    onLoadMore: () -> Unit,
) {
    val columnState = rememberLazyListState()

    LazyColumn(state = columnState) {
        items(images.chunked(5)) { gallery ->
            LazyRow {
                items(gallery) { currentItem ->
                    ItemCard(
                        modifier = Modifier
                            .heightIn(min = 150.dp, 180.dp)
                            .widthIn(min = 150.dp, max = 160.dp)
                            .clickable(onClick = { onItemClicked(currentItem) }),
                        nasaImageUIModel = currentItem,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    LoadMoreItems(
        columnState = columnState,
        onLoadMore,
    )
}

@Composable
private fun LoadMoreItems(
    columnState: LazyListState = rememberLazyListState(),
    onLoadMore: () -> Unit,
) {
    // Load more images when reaching the end of the list
    LaunchedEffect(columnState.canScrollForward) {
        Log.d("Testing", "LoadMoreItems")
        val totalItemCount = columnState.layoutInfo.totalItemsCount
        if (totalItemCount > 0 && !columnState.canScrollForward) {
            onLoadMore.invoke()
        }
    }
}
