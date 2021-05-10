package com.wsr.routings.show_status.message

import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.messageRoute(){
    get("/message"){
        call.respond(FreeMarkerContent("pages/message/index.ftl", mapOf("" to ""), ""))
    }
}
