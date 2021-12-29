package mainApp

import components.content.content
import components.header.header
import components.sidebar.Content
import components.sidebar.sidebar
import kotlinx.browser.localStorage
import mui.material.styles.ThemeProvider
import pages.empty.empty
import pages.preferences.preferences
import react.FC
import react.Props
import react.router.dom.BrowserRouter
import react.useMemo
import react.useState
import styles.KardsDarkTheme
import styles.KardsLightTheme
import styles.createTheme

val mainApp = FC<Props> {
    val contentList = useMemo {
        listOf(
            Content("/something-cool", "Something Cool", empty),
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
                value = contentList
            }
            content {
                value = contentList
            }
        }
    }
}
