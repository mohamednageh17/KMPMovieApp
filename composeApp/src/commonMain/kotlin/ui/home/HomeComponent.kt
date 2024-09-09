package ui.home

import androidx.compose.runtime.MutableState
import networking.model.MovieModel

interface HomeComponent {
    var movieList: MutableState<List<MovieModel>>

    var loading: MutableState<Boolean>

    fun setLoading(value: Boolean)

    fun updateList(list: List<MovieModel>)

    fun navigateToDetailsScreen(item: MovieModel?)

    fun navigateToAboutScreen()

    fun navigateToSettingsScreen()

    fun navigateToProfileScreen()
}