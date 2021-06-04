package com.wsr.routings.reply_message

import com.typesafe.config.ConfigFactory
import com.wsr.model.json.Challenge
import com.wsr.model.json.slack.Action
import com.wsr.services.send_message.SendMessageInterface
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.delay
import org.koin.ktor.ext.inject
import java.util.*

/**
 * メッセージの送信部分のルートの定義
 */
fun Route.replyMessageRoute(){

    //Postを飛ばす先を設定ファイルから読み込む処理
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val challengeMode = appConfig.property("reply.challengeMode").getString().lowercase(Locale.getDefault())

    //メッセージの作成や送信を行うクラス
    val sendMessageService by inject<SendMessageInterface>()

    /**
     * Slack APIからイベントを受け取り、メッセージを送信
     */
    post("/"){

        //Slackに登録する際に使用
        if(challengeMode == "true") {
            val challenge = call.receive<Challenge>()
            return@post call.respond(challenge)
        }
        delay(10000)

        //Slack APIから、イベントの情報を入手
        val action = call.receive<Action>()

        // Slack APIに、無事入手したことを報告(これをしないと定期的に送られてくる)
        call.respond(Challenge("www", "Got it", "www"))

        //headerの確認
        val headers = call.request.headers

        //1回目のメッセージなら
        if(headers["X-Slack-Retry-Num"] == null){
            //送るメッセージがあれば送る(nullであれば送らない)
            sendMessageService.makeReply(action)?.let {
                sendMessageService.sendMessage(it)
            }
        }else{
            println("Pass")
        }
    }
}
