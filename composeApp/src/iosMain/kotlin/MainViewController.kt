import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import io.ktor.client.engine.darwin.Darwin
import navigation.DefaultRootComponent
import networking.MovieApiClient
import networking.createHttpClient

fun MainViewController() = ComposeUIViewController  {

    val rootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(ApplicationLifecycle()))
    }

    App(
        rootComponent = rootComponent,
        apiClient = MovieApiClient(
            httpClient = createHttpClient(
                Darwin.create()
            )
        )
    )
}