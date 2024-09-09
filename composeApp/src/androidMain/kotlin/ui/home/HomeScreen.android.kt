package ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import networking.MovieApiClient
import ui.MovieCard
import utilis.onError
import utilis.onSuccess

@Composable
actual fun HomeScreen(
    component: HomeComponent, apiClient: MovieApiClient
) {
    val moviesList by component.movieList
    val loading by component.loading


    LaunchedEffect(key1 = Dispatchers.IO) {
        apiClient.fetchMovies().onSuccess {
            it.results?.let {
                withContext(Dispatchers.Main) {
                    component.setLoading(false)
                    component.updateList(it)
                }
            }

        }.onError {
            withContext(Dispatchers.Main) {
                component.setLoading(false)
            }
        }


    }

    moviesList.apply {
        if (this.isNotEmpty()) {

            Scaffold(
                topBar = {
                    TopAppBar(backgroundColor = Color.White, navigationIcon = {
                        Icon(Icons.Filled.Menu, "")
                        RectangleShape
                    }, windowInsets = WindowInsets.ime,
                        elevation = 5.dp,
                        title = { Text("Movie App") }
                    )
                },
                content = { padding ->
                    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 4.dp,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .animateContentSize()
                            .padding(padding),
                        content = {
                            items(this@apply.size) { index ->
                                MovieCard(this@apply[index], onClick = { movieModel ->
                                    component.navigateToDetailsScreen(item = movieModel)
                                })
                            }
                        }
                    )
                }
            )

        }
    }
    loading.let {
        if (it) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                    )
                })
        } else return
    }

}