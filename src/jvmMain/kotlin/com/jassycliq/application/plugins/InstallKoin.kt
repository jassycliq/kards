package com.jassycliq.application.plugins

import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger

fun Application.installKoin() {
    install(Koin) {
        SLF4JLogger()
    }
}
