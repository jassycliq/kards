import kotlinx.browser.document
import mainApp.mainApp
import org.koin.core.context.startKoin
import react.create
import react.dom.render

fun main() {
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
    }

    val container = document.getElementById("root")!!
    render(mainApp.create(), container)
}
