package com.wsr.routings

import com.typesafe.config.ConfigFactory
import com.wsr.model.h2.entities.User
import com.wsr.model.h2.tables.Users
import com.wsr.model.slack.Slack
import com.wsr.services.Service
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.config.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.replyMessageRoute(){

    //Postを投げるためのクライアントのインストール
    val client = HttpClient(CIO){
        install(JsonFeature)
    }

    //Postを飛ばす先を設定ファイルから読み込む処理
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val url = appConfig.property("slack.url").getString()

    /**
     * Slack APIからイベントを受け取り、メッセージを送信
     */
    post("/"){

        //Slack APIから、イベントの情報を入手
        val slack = call.receive<Slack>()

        //Slack APIに、無事入手したことを報告(これをしないと定期的に送られてくる)
        call.respondText("Got it")

        //データベースに、既に紹介文を送信したユーザーとして登録されているかどうか
        var isExist = false

        transaction {
            isExist = User.find { Users.userId eq slack.event.user }.count() > 0
        }

        if(!isExist){

            //メッセージを送信する処理
            launch {
                client.post(url) {
                    headers {
                        append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                    }

                    //メッセージ本文
                    body = Service.setMessage(slack.event.user)
                }
            }.join()

            //送信のログを吐く
            println("Sent")

            //データベースの、紹介文を送信したユーザーに登録
            transaction {
                User.new {
                    userId = slack.event.user
                }
            }

            //登録したことをログに吐く
            println("Registered")
        }else{

            //一度送信したことをログに吐く
            println("OUT")
        }
    }
}
