package com.jassycliq.application.plugins

import io.ktor.application.*
import io.ktor.features.*

fun Application.installCompression() {
    install(Compression) {
        gzip()
        deflate()
    }
}
