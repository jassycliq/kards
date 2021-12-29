package components.header

import csstype.*
import kotlinext.js.jso
import mui.material.*
import react.FC
import react.Props
import react.dom.aria.ariaLabel
import react.router.dom.Link
import styles.Sizes

external interface KardsProps : Props {
    var darkMode: Boolean?
    var darkModeClicked: (Boolean?) -> Unit
}

val header = FC<kardsProps> { props ->
    AppBar {
        position = "sticky"

        sx = jso {
            width = 100.pct
            height = Sizes.Header.height
            zIndex = ZIndex(1201)
        }

        Toolbar {
            Link {
                +"Kards"
                to =  "/"

                style = jso {
                    color = Color("inherit")
                    textDecoration = "inherit".asDynamic()
                    flexGrow = FlexGrow(1.0)
                    margin = 0.px
                    fontFamily = "'Roboto', 'Helvetica', 'Arial', 'sans-serif'".asDynamic()
                    fontWeight = FontWeight(400)
                    fontSize = 2.125.rem
                    lineHeight = "1.235".asDynamic()
                    letterSpacing = 0.00735.em
                }
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
