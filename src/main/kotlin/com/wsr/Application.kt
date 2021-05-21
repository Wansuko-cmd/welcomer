package com.wsr

import com.typesafe.config.ConfigFactory
import com.wsr.model.h2.DBController
import com.wsr.routings.mainRoute
import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.config.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    //Postを飛ばす先を設定ファイルから読み込む処理
    val appConfig = HoconApplicationConfig(ConfigFactory.load())

    val user = appConfig.property("account.user").getString()
    val password = appConfig.property("account.password").getString()

    install(Authentication){
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                if (credentials.name == user && credentials.password == password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }

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


    routing {
        get("/"){
            call.respondText("Hello")
        }
    }

//    mainRoute()
}
