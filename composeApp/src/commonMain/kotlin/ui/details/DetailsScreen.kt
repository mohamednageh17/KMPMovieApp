package ui.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import networking.model.MovieModel
import ui.MovieCard

@Composable
fun DetailsScreen(
    component: DetailsComponent, modifier: Modifier = Modifier
) {
    val movieModel by component.model

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.White,
            navigationIcon = {
                Icon(Icons.AutoMirrored.Default.ArrowBack,
                    "",
                    modifier = modifier.clickable { component.onBackPressed() })
                RectangleShape
            },
            windowInsets = WindowInsets.ime,
            elevation = 5.dp,
            title = { Text(movieModel?.title ?: "Invalid Movie") })
    }, content = { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            Text("Details Screen ${movieModel?.title}")
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                component.onBackPressed()
            }) {
                Text("Back")
            }
        }
    })

}