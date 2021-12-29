package com.jassycliq.application

import com.jassycliq.application.html.index
import com.jassycliq.application.plugins.installKoin
import com.jassycliq.application.plugins.installLocations
import com.jassycliq.application.plugins.installOAuth
import com.jassycliq.application.plugins.installSerialization
import com.jassycliq.application.routes.indexRoute
import com.jassycliq.application.routes.preferencesRoute
import com.jassycliq.application.routes.staticRoute
import com.jassycliq.application.routes.stylesRoute
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.cio.*
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

        get("/something-cool") {
            call.respondHtml(HttpStatusCode.OK, HTML::index)
        }
    }
}
