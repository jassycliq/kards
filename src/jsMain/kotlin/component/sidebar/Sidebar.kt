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

package component.sidebar

import csstype.BoxSizing
import csstype.Color
import csstype.FlexGrow
import csstype.FlexShrink
import csstype.Position
import csstype.em
import csstype.minus
import csstype.pct
import csstype.px
import kotlinext.js.jso
import mui.material.Divider
import mui.material.Drawer
import mui.material.DrawerAnchor
import mui.material.DrawerVariant
import mui.material.List
import mui.material.ListItemButton
import mui.material.ListItemButtonBaseProps
import react.FC
import react.Props
import react.router.dom.Link
import react.router.useLocation
import react.useEffect
import react.useState
import style.Sizes

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
        anchor = DrawerAnchor.left
        variant = DrawerVariant.permanent

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
                            @Suppress("UnsafeCastFromDynamic")
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
