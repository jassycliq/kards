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
import mui.material.styles.Mixins
import mui.material.styles.Palette
import mui.material.styles.ThemeOptions
import mui.material.styles.Transitions
import mui.material.styles.ZIndex
import mui.system.Breakpoints
import mui.system.ShapeOptions
import mui.system.Spacing
import react.Props

external interface Theme {
    var shape: ShapeOptions
    var breakpoints: Breakpoints
    var direction: dynamic /* String /* "ltr" */ | String /* "rtl" */ */
    var mixins: Mixins
    var overrides: dynamic
    var palette: Palette
    var props: Props
    var shadows: dynamic
    var spacing: Spacing
    var transitions: Transitions
    var typography: Typography
    var zIndex: ZIndex
}

@JsModule("@mui/material/styles/createTheme")
@JsNonModule
private external val createThemeModule: dynamic

/**
 * @param themeOptions Options for changing the theme (see Material-UI documentation)
 * @param args Further options, specifically you can change the locale of the theme. (See location
 *             guide of Material-UI documentation and the test Localization example.)
 */
@Suppress("UnsafeCastFromDynamic")
fun createTheme(themeOptions: ThemeOptions? = null, args: dynamic = null): Theme {

    // We shall just use default (i.e. blank) options if none are provided
    val ourThemeOptions = themeOptions ?: jso {  }

    return createThemeModule.default(ourThemeOptions, args)
}
