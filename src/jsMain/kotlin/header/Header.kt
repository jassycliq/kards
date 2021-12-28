package header

import csstype.*
import kotlinext.js.jso
import mui.material.*
import react.FC
import react.Props
import react.dom.aria.ariaLabel
import styles.Sizes

external interface kardsProps : Props {
    var darkMode: Boolean?
    var darkModeClicked: (Boolean?) -> Unit
}

val header = FC<kardsProps> { props ->
    AppBar {
        position = "fixed"

        sx = jso {
            width = 100.pct
            height = Sizes.Header.height
            zIndex = ZIndex(1201)
        }

        Toolbar {
            Typography {
                variant = "h4"
                +"Kards"

                sx = jso { flexGrow = FlexGrow(1.0) }
            }
            Box {
                component = "form".asDynamic()

                FormControl {
                    variant = "filled"
                    size = "small"

                    InputLabel {
                        htmlFor = "component-filled"
                        color = "secondary"
                        +"Search"
                        style = jso {
                            color = Color("#c4c4c4")
                        }
                    }
                    FilledInput {
                        ariaLabel = "Search"
                        disableUnderline = true

                        sx = jso {
                            borderRadius = 16.px
                            backgroundColor = "rgba(255, 255, 255, 0.2)".asDynamic()
                            color = Color("white")
                        }
                    }
                }
            }
            Switch {
                defaultChecked = props.darkMode
                onClick = { props.darkModeClicked(props.darkMode?.not()) }
            }
        }
    }
}
