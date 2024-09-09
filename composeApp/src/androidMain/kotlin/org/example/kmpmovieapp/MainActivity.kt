package org.example.kmpmovieapp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import io.ktor.client.engine.okhttp.OkHttp
import navigation.DefaultRootComponent
import networking.MovieApiClient
import networking.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiClient = MovieApiClient(httpClient = createHttpClient(OkHttp.create()))
        val rootComponent = DefaultRootComponent(defaultComponentContext(), apiClient)
        setContent {
            App(
                rootComponent = rootComponent,
                apiClient = apiClient,
            )
        }
    }
}

