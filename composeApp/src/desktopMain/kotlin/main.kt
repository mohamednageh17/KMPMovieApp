import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.ktor.client.engine.okhttp.OkHttp
import navigation.DefaultRootComponent
import networking.MovieApiClient
import networking.createHttpClient
import javax.swing.SwingUtilities

fun main() {
    val lifecycle = LifecycleRegistry()

    val apiClient = MovieApiClient(httpClient = createHttpClient(OkHttp.create()))
    val rootComponent = runOnUiThread {
        DefaultRootComponent(DefaultComponentContext(lifecycle), apiClient)
    }
    application {
        val windowState = rememberWindowState()
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "KMPMovieApp",
        ) {

            App(
                rootComponent = rootComponent,
                apiClient = apiClient
            )
        }
    }
}


internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}