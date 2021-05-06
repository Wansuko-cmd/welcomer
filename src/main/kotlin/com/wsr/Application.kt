package com.wsr

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    install(ContentNegotiation){
        json()
    }

    routing {
        post("/"){
            val challenge = call.receive<Challenge>()

            call.respond(challenge)
        }
    }
}
