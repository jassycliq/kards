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

package com.jassycliq.application

import com.jassycliq.application.html.index
import com.jassycliq.application.plugins.installKoin
import com.jassycliq.application.plugins.installLocations
import com.jassycliq.application.plugins.installOAuth
import com.jassycliq.application.plugins.installSerialization
import com.jassycliq.application.routes.apiRoute
import com.jassycliq.application.routes.indexRoute
import com.jassycliq.application.routes.preferencesRoute
import com.jassycliq.application.routes.staticRoute
import com.jassycliq.application.routes.stylesRoute
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.cio.EngineMain
import kotlinx.html.HTML

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    installKoin()
    installSerialization()
    installLocations()
    installOAuth()

    routing {
        staticRoute()
        stylesRoute()
        indexRoute()
        preferencesRoute()
        apiRoute()

        get("/something-cool") {
            call.respondHtml(HttpStatusCode.OK, HTML::index)
        }
    }
}
