package com.jassycliq.application.plugins

import io.ktor.application.*
import io.ktor.locations.*

fun Application.installLocations() {
    install(Locations)
}
