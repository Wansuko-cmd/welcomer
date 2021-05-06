package com.wsr.routings

import com.typesafe.config.ConfigFactory
import com.wsr.entities.Challenge
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.config.*
import kotlinx.coroutines.launch

fun Application.route(){
    val client = HttpClient(CIO)
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val url = appConfig.property("slack.url").getString()

    routing {
        get("/"){
            call.respondText("This server is working!")

            val job = launch {
                val response: HttpResponse = client.post(url) {
                    body = """{"text": "Hello World"}"""
                }
            }
            job.join()
        }

        post("/"){
            val challenge = call.receive<Challenge>()
            val job = launch {
                val response: HttpResponse = client.post(url) {
                    body = """{"text": "Hello World"}"""
                }
            }
            job.join()

            call.respond(challenge)
        }
    }
}
