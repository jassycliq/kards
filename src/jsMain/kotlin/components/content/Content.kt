package components.content

import components.sidebar.Content
import csstype.FlexGrow
import kotlinext.js.jso
import mui.material.Box
import react.FC
import react.Props
import react.dom.html.ReactHTML
import routes.sidebarRoutes

external interface ContentProps : Props {
    var value: Iterable<Content>
}

val content = FC<ContentProps> { props ->
    Box {
        id = "kards-content"
        component = ReactHTML.main

        sx = jso {
            flexGrow = FlexGrow(8.0)
        }

        sidebarRoutes {
            value = props.value
        }
    }
}
