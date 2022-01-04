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

package page.preferences

import com.jassycliq.application.model.MediaServer
import csstype.Color
import csstype.em
import csstype.pct
import csstype.px
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import kotlinext.js.jso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mui.material.Divider
import mui.material.List
import mui.material.ListItemButton
import mui.material.ListItemButtonBaseProps
import mui.material.Paper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import react.FC
import react.Props
import react.router.dom.Link
import react.useEffect
import react.useState

class Preferences : KoinComponent {
    private val httpClient by inject<HttpClient>()
    private val coroutineScope by inject<CoroutineScope>()

    val preferences = FC<Props> {
        var mediaServerList: List<MediaServer>? by useState()

        useEffect {
            if (mediaServerList == null) {
                coroutineScope.launch {
                    mediaServerList = httpClient.request<List<MediaServer>>("/api/media-server")
                }
            }
        }

        Paper {
            square = true

            sx = jso {
                width = 100.pct
                height = 100.pct
            }
            List {
                mediaServerList?.map { mediaServer ->
                    console.log("Media Server = $mediaServer")
                    ListItemButton {
                        // TODO: Needs an ability to set generic type to `ListItemButton` component [MUI]
                        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
                        this as ListItemButtonBaseProps

                        sx = jso {
                            padding = 0.px
                        }

                        Link {
                            +"${mediaServer.name} ${mediaServer.scheme.toString().lowercase()}://${mediaServer.url}"
                            to = mediaServer.id.toString()
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
}
