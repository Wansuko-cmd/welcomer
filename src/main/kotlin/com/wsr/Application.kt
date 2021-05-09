package com.wsr

import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import com.wsr.routings.route
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){
    install(ContentNegotiation){
        json()
    }

    Database.connect("jdbc:h2:mem:ktor_db;DB_CLOSE_DELAY=-1", "org.h2.Driver")

    transaction {
        SchemaUtils.create(Users)
    }

    route()
}
