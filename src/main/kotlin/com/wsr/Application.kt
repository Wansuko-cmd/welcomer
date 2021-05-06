package com.wsr

import com.wsr.routings.route
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){
    install(ContentNegotiation){
        json()
    }

    route()
}
