package com.wsr


import io.ktor.application.*

import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){
//
//    //Postを飛ばす先を設定ファイルから読み込む処理
//    val appConfig = HoconApplicationConfig(ConfigFactory.load())
//
//    val user = appConfig.property("account.user").getString()
//    val password = appConfig.property("account.password").getString()
//
//    install(Authentication){
//        basic("auth-basic") {
//            realm = "Access to the '/' path"
//            validate { credentials ->
//                if (credentials.name == user && credentials.password == password) {
//                    UserIdPrincipal(credentials.name)
//                } else {
//                    null
//                }
//            }
//        }
//    }
//
//    //Serializerのインストール
//    install(ContentNegotiation){
//        json(Json {
//            isLenient = true
//            ignoreUnknownKeys = true
//        })
//    }
//
//    //FreeMakerのインストール
//    install(FreeMarker){
//        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
//        outputFormat = HTMLOutputFormat.INSTANCE
//    }
//
//
//    DBController.init()


    routing {
        get("/"){
            call.respondText("Hello")
        }
    }

//    mainRoute()
}
