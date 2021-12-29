package com.jassycliq.application.routes

import io.ktor.http.content.*
import io.ktor.routing.*

fun Route.staticRoute() {
    static("/static") {
        resources()
    }
}
