package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import networking.MovieApiClient
import networking.model.MovieModel
import ui.details.DefaultDetailsComponent
import ui.home.DefaultHomeComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    val apiClient: MovieApiClient
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, RootComponent.Child>>
        get() = childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Home,
            handleBackButton = true,
            childFactory = ::child
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Home -> RootComponent.Child.Home(
                DefaultHomeComponent(componentContext = componentContext,
                    apiClient = apiClient,
                    onMovieClicked = { movieModel -> navigation.push(Config.Details(movieModel)) })
            )

            is Config.Details -> RootComponent.Child.Details(
                DefaultDetailsComponent(
                    componentContext = componentContext,
                    movieModel = config.movieModel,
                    onBackPressed = { navigation.pop() }
                ),
            )

        }


    @Serializable
    private sealed interface Config {
        @Serializable
        data object Home : Config

        @Serializable
        data class Details(val movieModel: MovieModel?) : Config

    }
}