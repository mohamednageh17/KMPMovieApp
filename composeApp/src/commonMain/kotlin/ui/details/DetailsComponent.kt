package ui.details

import androidx.compose.runtime.MutableState
import com.arkivanov.decompose.value.Value
import networking.model.MovieModel

interface DetailsComponent {
    val model: MutableState<MovieModel?>

    fun onBackPressed()
}