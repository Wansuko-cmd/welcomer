package com.wsr.routings

import com.typesafe.config.ConfigFactory
import com.wsr.model.slack.Action
import com.wsr.services.SendMessageService
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
        val action = call.receive<Action>()

        //Slack APIに、無事入手したことを報告(これをしないと定期的に送られてくる)
        call.respondText("Got it")

        //送るメッセージがあれば送る(nullであれば送らない)
        SendMessageService.sendMessage(action)?.let {

            //メッセージを送信する処理
            launch {
                client.post(url) {
                    headers {
                        append(io.ktor.http.HttpHeaders.ContentType, "application/json")
                    }

                    //メッセージ本文
                    body = it
                }
            }.join()

            println("Send")
        }
    }
}
