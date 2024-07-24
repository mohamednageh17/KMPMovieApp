import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview



@Composable
@Preview
fun App(
    testActual: TestActual
) {
    MaterialTheme {
      Scaffold(
          topBar = {
              TopAppBar(
                  title = {
                      Text("Hello KMP from ${testActual.getTarget()}")
                  }
              )
          }
      ) {
          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .fillMaxHeight(),
              contentAlignment = Alignment.Center
          ) {
              Column(
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  val state = rememberSaveable { mutableStateOf(0) }
                  Spacer(modifier = Modifier.height(20.dp))

                  Row(
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Button(
                          onClick = {
                              state.value = state.value.dec()
                          }) {
                          Text("-")
                      }
                      Spacer(modifier = Modifier.width(20.dp))
                      Text("${state.value}")
                      Spacer(modifier = Modifier.width(20.dp))
                      Button(
                          onClick = {
                              state.value = state.value.inc()
                          }) {
                          Text("+")
                      }
                  }
              }
          }
      }
    }
}