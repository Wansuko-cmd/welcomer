package com.wsr.routings

import io.ktor.application.*
import io.ktor.routing.*

fun Application.mainRoute(){
    routing {
        replyMessageRoute()
        showStatusRoute()
    }
}
