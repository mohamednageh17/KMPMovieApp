import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import networking.MovieApiClient
import networking.createHttpClient

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPMovieApp",
    ) {
        App(
            testActual = TestActual(),
            apiClient = MovieApiClient(httpClient = createHttpClient(OkHttp.create()))
        )
    }
}