import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import navigation.RootComponent
import navigation.RootContent
import networking.MovieApiClient
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(
    rootComponent: RootComponent,
    apiClient: MovieApiClient
) {

    MaterialTheme {
        RootContent(
            component = rootComponent,
            apiClient = apiClient
        )
    }
}