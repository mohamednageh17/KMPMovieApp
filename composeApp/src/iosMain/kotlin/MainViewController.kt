import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import networking.MovieApiClient
import networking.createHttpClient

fun MainViewController() = ComposeUIViewController {
    App(
        testActual = TestActual(),
        apiClient = MovieApiClient(
            httpClient = createHttpClient(
                Darwin.create()
            )
        )
    )
}