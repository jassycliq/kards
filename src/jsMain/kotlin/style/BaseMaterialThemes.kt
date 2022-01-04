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

package style

import kotlinext.js.jso
import mui.material.styles.MixinsOptions
import mui.material.styles.PaletteOptions
import mui.material.styles.ThemeOptions
import mui.material.styles.TransitionsOptions

object KardsLightTheme : ThemeOptions {
    override var components: dynamic = jso {}
    override var mixins: MixinsOptions? = jso {}
    override var palette: PaletteOptions? = jso {
        mode = "light"
        primary = jso {
            main = "#3f51b5"
        }
        secondary = jso {
            main = "#f50057"
        }
    }
    override var shadows: dynamic = jso {}
    override var transitions: TransitionsOptions? = jso {}
    override var typography: dynamic = jso {}
    override var unstable_strictMode: Boolean? = jso {}
    override var zIndex: dynamic = jso {}
}

object KardsDarkTheme : ThemeOptions {
    override var components: dynamic = jso {}
    override var mixins: MixinsOptions? = jso {}
    override var palette: PaletteOptions? = jso {
        mode = "dark"
        primary = jso {
            main = "#3f51b5"
        }
        secondary = jso {
            main = "#f50057"
        }
    }
    override var shadows: dynamic = jso {}
    override var transitions: TransitionsOptions? = jso {}
    override var typography: dynamic = jso {}
    override var unstable_strictMode: Boolean? = jso {}
    override var zIndex: dynamic = jso {}
}
