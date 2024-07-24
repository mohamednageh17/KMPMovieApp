package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import networking.API
import networking.model.MovieModel

@Composable
fun MovieCard(item: MovieModel?, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
            .width(IntrinsicSize.Min)
            .clip(RoundedCornerShape(5)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = "${API.moviePoster}/${item?.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
               // .size(150.dp, 300.dp)
                .clip(RoundedCornerShape(8.dp))
        )

    }
}