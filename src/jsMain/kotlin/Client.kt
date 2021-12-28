import content.content
import header.header
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.localStorage
import mui.material.styles.*
import org.w3c.dom.HTMLDivElement
import preferences.preferences
import react.*
import react.router.dom.BrowserRouter
import routes.sidebarRoutes
import sidebar.Content
import sidebar.sidebar
import styles.KardsDarkTheme
import styles.KardsLightTheme
import styles.createTheme

fun main() {
    val container = document.createElement("div").unsafeCast<HTMLDivElement>().apply {
        style.minHeight = "100vh"
        style.minWidth = "100vw"
        style.display = "flex"
        style.flexDirection = "column"

        document.body!!.appendChild(this)
    }
    render(app.create(), container)
}

private val app = FC<Props> {
    val content = useMemo {
        listOf(
            Content("/something-cool", "Something Cool", content),
            Content("/preferences", "Preferences", preferences),
        )
    }

    val localState = localStorage.getItem("darkMode").toBoolean()

    var darkModeState: Boolean? by useState(localState)

    ThemeProvider {
        theme = createTheme(if (darkModeState == true) KardsDarkTheme else KardsLightTheme)

        BrowserRouter {
            header {
                darkMode = darkModeState
                darkModeClicked = { newMode ->
                    localStorage.setItem("darkMode", newMode.toString())
                    darkModeState = newMode
                }
            }
            sidebar {
                value = content
            }
            sidebarRoutes {
                value = content
            }
        }
    }
}
