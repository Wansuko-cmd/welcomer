package com.wsr.routings.reply_message

import com.typesafe.config.ConfigFactory
import com.wsr.model.json.Challenge
import com.wsr.model.json.slack.Action
import com.wsr.services.SendMessageService
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Route.replyMessageRoute(){

    //Postを飛ばす先を設定ファイルから読み込む処理
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val challengeMode = appConfig.property("reply.challengeMode").getString().lowercase(Locale.getDefault())
    /**
     * Slack APIからイベントを受け取り、メッセージを送信
     */
    post("/"){

        //Slackに登録する際に使用
        if(challengeMode == "true") {
            val challenge = call.receive<Challenge>()
            call.respond(challenge)
        }

        //Slack APIから、イベントの情報を入手
        val action = call.receive<Action>()

        // Slack APIに、無事入手したことを報告(これをしないと定期的に送られてくる)
        call.respond(Challenge("www", "Got it", "www"))

        //送るメッセージがあれば送る(nullであれば送らない)
        SendMessageService.makeReply(action)?.let {
            SendMessageService.sendMessage(it)
        }
    }
}
