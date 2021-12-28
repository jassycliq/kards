package styles

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
