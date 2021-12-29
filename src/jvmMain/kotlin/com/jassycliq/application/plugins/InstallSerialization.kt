package com.jassycliq.application.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.installSerialization() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}
