package com.wsr

import com.wsr.model.h2.DBController
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import com.wsr.routings.mainRoute
import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.*
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

    //FreeMakerのインストール
    install(FreeMarker){
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }


    DBController.init()
    DBController.seeding()

    mainRoute()
}
