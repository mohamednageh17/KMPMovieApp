package org.example.kmpmovieapp

import App
import TestActual
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.engine.okhttp.OkHttp
import networking.MovieApiClient
import networking.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                testActual = TestActual(),
                apiClient = MovieApiClient(httpClient = createHttpClient(OkHttp.create())),)
        }
    }
}

