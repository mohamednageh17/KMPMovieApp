package navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import networking.MovieApiClient
import ui.home.HomeScreen
import ui.details.DetailsScreen

@Composable
fun RootContent(
    component: RootComponent, apiClient: MovieApiClient, modifier: Modifier = Modifier
) {

    Children(
        stack = component.stack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Home -> HomeScreen(
                component = child.component, apiClient = apiClient
            )

            is RootComponent.Child.Details -> DetailsScreen(
                component = child.component,
            )
        }
    }


}