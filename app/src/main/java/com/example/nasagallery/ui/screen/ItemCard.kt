package com.example.nasagallery.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.nasagallery.data.model.NasaImageUIModel

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    nasaImageUIModel: NasaImageUIModel,
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
        ) {
            Image(
                painter = rememberAsyncImagePainter(nasaImageUIModel.thumbnailUrl),
                contentDescription = nasaImageUIModel.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = nasaImageUIModel.title,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
