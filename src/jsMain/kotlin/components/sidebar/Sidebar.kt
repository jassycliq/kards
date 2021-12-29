package components.sidebar

import csstype.*
import kotlinext.js.jso
import mui.material.*
import react.FC
import react.Props
import react.router.dom.Link
import react.router.useLocation
import react.useEffect
import react.useState
import styles.Sizes

external interface SidebarProps : Props {
    var value: Iterable<Content>
}

val sidebar = FC<SidebarProps> { props ->
    val prevLocation = useLocation()
    var activeIndex: Content? by useState()

    useEffect {
        val prevIndex = props.value.find { it.key == prevLocation.pathname }
        activeIndex = prevIndex
    }

    Drawer {
        anchor = "left"
        variant = "permanent"

        sx = jso {
            flexGrow = FlexGrow(1.0)
            flexShrink = FlexShrink(1.0)
            height = 100.pct - Sizes.Header.height
        }

        PaperProps = jso {
            sx = jso {
                boxSizing = BoxSizing.borderBox
                position = Position.sticky
            }
        }

        List {
            props.value.map { content ->
                ListItemButton {
                    // TODO: Needs an ability to set generic type to `ListItemButton` component [MUI]
                    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
                    this as ListItemButtonBaseProps

                    selected = activeIndex == content
                    onClick = { activeIndex = content }

                    sx = jso {
                        padding = 0.px
                    }

                    Link {
                        +content.name
                        to = content.key
                        style = jso {
                            width = 100.pct
                            height = 100.pct
                            paddingTop = 8.px
                            paddingBottom = 8.px
                            paddingLeft = 16.px
                            fontSize = 1.17.em
                            color = Color("inherit")
                            textDecoration = "inherit".asDynamic()
                        }
                    }
                }
                Divider()
            }
        }
    }
}

data class Content(
    val key: String,
    val name: String,
    val element: FC<Props>,
)
