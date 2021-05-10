package com.wsr.routings.show_status.home

import com.wsr.model.h2.entities.SentMessage
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.homeRoute(){

    /**
     * 紹介文を送信したユーザーのリストを表示
     */
    get("/"){

        var sentMessages = listOf<SentMessage>()


        //データベースに登録されているユーザーを抽出
        transaction {
            sentMessages = SentMessage.all().toList()
        }

        call.respond(FreeMarkerContent("views/pages/index.ftl", mapOf("sentMessages" to sentMessages), ""))
    }
}
