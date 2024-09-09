package navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import networking.model.MovieModel
import ui.details.DetailsComponent
import ui.home.HomeComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class Details(val component: DetailsComponent) : Child

    }
}