package ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import networking.model.MovieModel

class DefaultDetailsComponent(
    componentContext: ComponentContext,
    val movieModel: MovieModel?,
    private val onBackPressed: () -> Unit
) : DetailsComponent, ComponentContext by componentContext {

    override val model: MutableState<MovieModel?> = mutableStateOf(movieModel)

    override fun onBackPressed() = onBackPressed.invoke()
}