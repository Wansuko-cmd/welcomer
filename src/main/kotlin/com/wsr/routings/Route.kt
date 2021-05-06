package com.wsr.routings

import com.wsr.entities.Challenge
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.route(){
    routing {
        get("/"){
            call.respondText("This server is working!")
        }

        post("/"){
            val challenge = call.receive<Challenge>()

            call.respond(challenge)
        }
    }
}
