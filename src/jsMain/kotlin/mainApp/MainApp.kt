/*
 * MIT License
 *
 * Copyright (c) 2021-2022 Jose Salgado
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mainApp

import component.content.content
import component.header.header
import component.sidebar.Content
import component.sidebar.sidebar
import kotlinx.browser.localStorage
import mui.material.styles.ThemeProvider
import page.empty.empty
import page.preferences.Preferences
import react.FC
import react.Props
import react.router.dom.BrowserRouter
import react.useMemo
import react.useState
import style.KardsDarkTheme
import style.KardsLightTheme
import style.createTheme

val mainApp = FC<Props> {
    val contentList = useMemo {
        listOf(
            Content("/something-cool", "Something Cool", empty),
            Content("/preferences", "Preferences", Preferences().preferences),
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
