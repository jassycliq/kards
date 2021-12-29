package com.jassycliq.application.html

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
