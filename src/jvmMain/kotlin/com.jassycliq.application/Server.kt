package com.jassycliq.application

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.embeddedServer
import kotlinx.css.*
import kotlinx.html.*

fun HTML.index() {
    head {
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/kards.js") {}
    }
}

fun main() {
    embeddedServer(CIO, port = 8080, host = "127.0.0.1", watchPaths = listOf("classes", "resources")) {
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            get("/preferences") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            get("/something-cool") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }

            get("/styles.css") {
                call.respondCss {
                    body {
                        margin(0.px)
                        display = Display.flex
                        height = 100.vh
                        width = 100.vw
                    }
                }
            }

            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
