package com.wsr.routings

import com.typesafe.config.ConfigFactory
import com.wsr.entities.Challenge
import com.wsr.services.service
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.config.*
import kotlinx.coroutines.launch
import java.net.http.HttpHeaders

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
            val job = launch {
                client.post(url) {
                    headers {
                        append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                    }
                    body = service()
                }
            }
            job.join()

            call.respond(challenge)
        }
    }
}
