/*
 * MIT License
 *
 * Copyright (c) 2021-2022 Jose Salgado
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package component.header

import csstype.Color
import csstype.FlexGrow
import csstype.FontWeight
import csstype.ZIndex
import csstype.em
import csstype.pct
import csstype.px
import csstype.rem
import kotlinext.js.jso
import mui.material.AppBar
import mui.material.AppBarPosition
import mui.material.Box
import mui.material.FilledInput
import mui.material.FormControl
import mui.material.FormControlVariant
import mui.material.FormLabelColor
import mui.material.InputLabel
import mui.material.Switch
import mui.material.Toolbar
import react.FC
import react.Props
import react.dom.aria.ariaLabel
import react.router.dom.Link
import style.Sizes

external interface KardsProps : Props {
    var darkMode: Boolean?
    var darkModeClicked: (Boolean?) -> Unit
}

@Suppress("UnsafeCastFromDynamic")
val header = FC<KardsProps> { props ->
    AppBar {
        position = AppBarPosition.sticky

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
                    variant = FormControlVariant.filled
                    size = "small".asDynamic()
                    sx = jso { minWidth = 256.px }

                    InputLabel {
                        htmlFor = "component-filled"
                        color = FormLabelColor.secondary
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
