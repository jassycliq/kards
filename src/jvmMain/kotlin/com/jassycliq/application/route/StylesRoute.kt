/*
 * MIT License
 *
 * Copyright (c) 2022 Jose Salgado
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

package com.jassycliq.application.route

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.css.CssBuilder
import kotlinx.css.Display
import kotlinx.css.FlexWrap
import kotlinx.css.body
import kotlinx.css.display
import kotlinx.css.flexWrap
import kotlinx.css.height
import kotlinx.css.margin
import kotlinx.css.px
import kotlinx.css.vh
import kotlinx.css.vw
import kotlinx.css.width

fun Route.stylesRoute() {
    get("/styles.css") {
        call.respondCss {
            body {
                margin(0.px)
            }
            rule("#root") {
                display = Display.flex
                flexWrap = FlexWrap.wrap
                height = 100.vh
                width = 100.vw
            }
        }
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
