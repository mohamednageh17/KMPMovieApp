package ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import networking.MovieApiClient
import networking.model.MovieModel

class DefaultHomeComponent(
    componentContext: ComponentContext,
    val apiClient: MovieApiClient,
    private val onMovieClicked: (MovieModel?) -> Unit,
) : HomeComponent, ComponentContext by componentContext {


    override var movieList: MutableState<List<MovieModel>> = mutableStateOf(emptyList())

    override var loading: MutableState<Boolean> = mutableStateOf(true)

    override fun setLoading(value: Boolean) {
        loading.value = value
    }

    override fun updateList(list: List<MovieModel>) {
        movieList.value = list
    }

    override fun navigateToDetailsScreen(item: MovieModel?) = onMovieClicked.invoke(item)

}