import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import networking.MovieApiClient
import networking.model.MovieResponse
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.MovieList
import utilis.onError
import utilis.onSuccess


@Composable
@Preview
fun App(
    testActual: TestActual,
    apiClient: MovieApiClient
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Hello KMP from ${testActual.getTarget()}")
                    }
                )
            },

        ) {
            val loading = rememberSaveable { mutableStateOf<Boolean?>(true) }

            val movieState = rememberSaveable { mutableStateOf<MovieResponse?>(null) }
            val errorState = rememberSaveable { mutableStateOf<String?>(null) }

            val scope = rememberCoroutineScope()
            scope.launch() {
                apiClient.fetchMovies().onSuccess {
                    movieState.value = it
                    loading.value = null
                }.onError {
                    errorState.value = it.toString()
                    loading.value = null
                }
            }

            loading.value?.let {
                if (it) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        content = {
                            CircularProgressIndicator(
                                modifier = Modifier.width(64.dp),
                            )
                        }
                    )
                }

            }

            movieState.value?.let {
                MovieList(it.results)
            }

            errorState.value?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        Text(
                            it, style = TextStyle(
                                color = Color.Red
                            )
                        )
                    }
                )
            }
        }
    }
}