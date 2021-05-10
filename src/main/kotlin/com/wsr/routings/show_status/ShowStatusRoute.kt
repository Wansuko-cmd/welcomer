package com.wsr.routings

import com.wsr.model.Message
import com.wsr.model.h2.entities.SentMessage
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.showStatusRoute(){

    /**
     * 紹介文を送信したユーザーのリストを表示
     */
    get("/"){

        var sentMessages = listOf<SentMessage>()


        //データベースに登録されているユーザーを抽出
        transaction {
            sentMessages = SentMessage.all().toList()
        }

        call.respond(FreeMarkerContent("pages/index.ftl", mapOf("sentMessages" to sentMessages), ""))
    }

    /**
     * データベースに入れてある、紹介文を送信したユーザーリストを削除
     */
    get("/delete"){
        transaction {
            Users.deleteAll()
        }
        call.respondText("Success")
    }

    get("/message"){
        call.respond(FreeMarkerContent("pages/message/index.ftl", mapOf("" to ""), ""))
    }

    get("/introduction"){
        call.respond(FreeMarkerContent("pages/introduction/index.ftl", mapOf("" to ""), ""))
    }
}
