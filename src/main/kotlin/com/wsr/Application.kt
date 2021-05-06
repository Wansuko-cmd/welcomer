package com.wsr

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){
    routing {
        get("/"){
            call.respondText("Hello World")
        }
    }
}
