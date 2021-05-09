package com.wsr.routings

import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import io.ktor.application.*
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
        val list = mutableListOf<String>()

        //データベースに登録されているユーザーを抽出
        transaction {
            val users = User.all()
            users.forEach{ user -> list.add(user.userId) }
        }

        call.respondText(list.toString())
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
