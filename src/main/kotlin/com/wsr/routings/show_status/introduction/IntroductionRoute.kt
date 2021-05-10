package com.wsr.routings.show_status.introduction

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.freemarker.*

fun Route.introductionRoute(){
    //Postを飛ばす先を設定ファイルから読み込む処理
    val appConfig = HoconApplicationConfig(ConfigFactory.load())

    get("/introduction"){
        val introduction = appConfig.property("slack.introduction").getString()
        call.respond(FreeMarkerContent(
            "pages/introduction/index.ftl",
            mapOf("introduction" to introduction),
            ""
        ))
    }
}
