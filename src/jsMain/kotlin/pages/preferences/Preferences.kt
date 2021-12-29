package pages.preferences

import csstype.pct
import kotlinext.js.jso
import mui.material.Paper
import react.FC
import react.Props

val preferences = FC<Props> {
    Paper {
        square = true

        sx = jso {
            width = 100.pct
            height = 100.pct
        }
    }
}
