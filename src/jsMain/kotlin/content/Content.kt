package content

import csstype.*
import kotlinext.js.jso
import mui.material.Box
import react.FC
import react.Props
import react.dom.html.ReactHTML.main
import styles.Sizes

val content = FC<Props> {
    Box {
        id = "kards-content"
        component = main

        sx = jso {
            flex = "1".asDynamic()
            marginTop = Sizes.Header.height
            padding = 30.px
        }
    }
}
