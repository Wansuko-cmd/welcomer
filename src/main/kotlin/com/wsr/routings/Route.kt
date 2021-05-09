package com.wsr.routings

import com.typesafe.config.ConfigFactory
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import com.wsr.model.slack.Slack
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.route(){
    val client = HttpClient(CIO){
        install(JsonFeature)
    }
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val url = appConfig.property("slack.url").getString()

    routing {
        get("/"){

            val list = mutableListOf<String>()

                transaction {
                    val users = User.all()
                    users.forEach{ user ->
                        println(user.userId)
                        list.add(user.userId)
                    }
                }

            call.respondText(list.toString())
        }

        post("/"){

            val slack = call.receive<Slack>()

            call.respondText("Got it")

            var isExist = false

            transaction {
                isExist = User.find { Users.userId eq slack.event.user }.count() > 0
            }

            if(!isExist){

                launch {
                    client.post(url) {
                        headers {
                            append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                        }
                        body = Service.setMessage(slack.event.user)
                    }
                }.join()

                println("Sent")

                transaction {
                    User.new {
                        userId = slack.event.user
                    }
                }
                println("Registered")
            }else{
                println("OUT")
            }
        }

        get("/delete"){
            transaction {
                Users.deleteAll()
            }
            call.respondText("Success")
        }
    }
}
