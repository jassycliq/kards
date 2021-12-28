package styles

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
