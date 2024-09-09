package ui.home

import androidx.compose.runtime.Composable
import networking.MovieApiClient

@Composable
expect fun HomeScreen(
    component: HomeComponent,
    apiClient: MovieApiClient

)