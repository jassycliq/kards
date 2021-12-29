package routes

import components.sidebar.Content
import react.FC
import react.Props
import react.create
import react.router.Route
import react.router.Routes

external interface SidebarRoutesProps : Props {
    var value: Iterable<Content>
}

val sidebarRoutes = FC<SidebarRoutesProps> { props ->
    Routes {
        props.value.map { content ->
            Route {
                path = content.key
                element = content.element.create()
            }
        }
    }
}
