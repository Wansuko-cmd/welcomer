package com.wsr

import com.wsr.model.h2.tables.Users
import com.wsr.routings.mainRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    //Serializerのインストール
    install(ContentNegotiation){
        json(Json {
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    //H2のセットアップ
    Database.connect("jdbc:h2:mem:ktor_db;DB_CLOSE_DELAY=-1", "org.h2.Driver")
    transaction {
        SchemaUtils.create(Users)
    }

    mainRoute()
}
