package preferences

import csstype.minus
import csstype.pct
import kotlinext.js.jso
import mui.material.Paper
import react.FC
import react.Props
import styles.Sizes

val preferences = FC<Props> {
    Paper {
        square = true
        sx = jso {
            height = 100.pct - Sizes.Header.height
            width = 100.pct - Sizes.Sidebar.width
            marginTop = Sizes.Header.height
            marginLeft = Sizes.Sidebar.width
        }
    }
}
