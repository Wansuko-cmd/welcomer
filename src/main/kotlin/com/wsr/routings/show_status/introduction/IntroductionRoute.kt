package com.wsr.routings.show_status.introduction

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.freemarker.*

fun Route.introductionRoute(){
    get("/introduction"){
        call.respond(FreeMarkerContent("pages/introduction/index.ftl", mapOf("" to ""), ""))
    }
}
