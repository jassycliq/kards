package com.jassycliq.application.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.css.*

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
