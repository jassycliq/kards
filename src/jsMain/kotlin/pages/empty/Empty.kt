package pages.empty

import csstype.pct
import kotlinext.js.jso
import mui.material.Box
import react.FC
import react.Props

val empty = FC<Props> {
    Box {
        sx = jso {
            width = 100.pct
            height = 100.pct
        }
    }
}
