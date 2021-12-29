package com.jassycliq.application.routes

import com.jassycliq.application.html.index
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import kotlinx.html.HTML

fun Route.indexRoute() {
    get("/") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
    }
}
