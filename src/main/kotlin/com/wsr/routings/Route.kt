package com.wsr.routings

import com.typesafe.config.ConfigFactory
import com.wsr.entities.Challenge
import com.wsr.services.Service
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.client.request.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.config.*
import kotlinx.coroutines.launch

fun Application.route(){
    val client = HttpClient(CIO){
        install(JsonFeature)
    }
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val url = appConfig.property("slack.url").getString()

    routing {
        get("/"){
            call.respondText("This server is working!")
        }

        post("/"){
            val challenge = call.receive<Challenge>()
            launch {
                client.post(url) {
                    headers {
                        append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                    }
                    body = Service.setMessage()
                }
            }.join()

            call.respond(challenge)
        }
    }
}
