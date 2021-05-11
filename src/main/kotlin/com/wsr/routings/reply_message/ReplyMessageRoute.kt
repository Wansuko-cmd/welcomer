package com.wsr.routings.reply_message

import com.wsr.model.Challenge
import com.wsr.model.slack.Action
import com.wsr.services.SendMessageService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.replyMessageRoute(){
    /**
     * Slack APIからイベントを受け取り、メッセージを送信
     */
    post("/"){

        //Slack APIから、イベントの情報を入手
        val action = call.receive<Action>()

        //Slack APIに、無事入手したことを報告(これをしないと定期的に送られてくる)
        call.respond(Challenge("www", "Got it", "www"))

        //送るメッセージがあれば送る(nullであれば送らない)
        SendMessageService.makeReply(action)?.let {
            SendMessageService.sendMessage(it)
        }
    }
}
