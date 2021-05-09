package com.wsr.routings

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

        //紹介文を送信したユーザーのリスト
        var users = listOf<User>()

        //データベースに登録されているユーザーを抽出
        transaction {
            users = User.all().toList()
        }

        call.respond(FreeMarkerContent("index.ftl", mapOf("users" to users), ""))
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
}
