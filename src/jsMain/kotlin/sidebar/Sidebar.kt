package sidebar

import csstype.*
import kotlinext.js.jso
import mui.material.*
import react.*
import react.router.dom.Link
import react.router.useLocation
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
        open = true
        variant = "permanent"

        PaperProps = jso {
            sx = jso {
                width = Sizes.Sidebar.width
                boxSizing = BoxSizing.borderBox
            }
        }

        sx = jso {
            flexShrink = FlexShrink(0.0)
            width = Sizes.Sidebar.width
        }
        Toolbar {

        }
        List {
            sx = jso {
                width = Sizes.Sidebar.width
            }

            props.value.map { content ->
                ListItemButton {
                    // TODO: Needs an ability to set generic type to `ListItemButton` component [MUI]
                    this as ListItemButtonBaseProps

                    selected = activeIndex == content
                    onClick = { activeIndex = content }

                    sx = jso {
                        padding = 0.px
                    }

                    Link {
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
                        +content.name
                    }
                }
            }
        }
    }
}

data class Content(
    val key: String,
    val name: String,
    val element: FC<Props>,
)
