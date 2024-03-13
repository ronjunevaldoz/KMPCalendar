import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.ronjunevaldoz.calendardemo.App

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "Calendar Demo") {
            App()
        }
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}