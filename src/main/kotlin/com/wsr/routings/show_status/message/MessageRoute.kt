package com.wsr.routings.show_status.message

import com.wsr.model.json.message.Message
import com.wsr.model.db.DBController
import com.wsr.services.send_message.SendMessageInterface
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.messageRoute(){

    val dbController by inject<DBController>()
    val sendMessageService by inject<SendMessageInterface>()

    /**
     * 送信するメッセージの作成画面の表示
     */
    get("/message"){
        call.respond(FreeMarkerContent("views/pages/message/index.ftl", mapOf("" to ""), ""))
    }

    /**
     * 送信が完了したことを表す画面の表示
     */
    get("/message/done"){
        call.respond(FreeMarkerContent("views/pages/message/done.ftl", mapOf("" to ""), ""))
    }

    /**
     * メッセージを送信するためのPost先
     */
    post("/message"){
        val params = call.receiveParameters()

        params["body"]?.let {
            sendMessageService.sendMessage(Message(it))
            dbController.makeSentMessageHistory("Owner", "Webから送信", it)
        }

        call.respondRedirect("/message/done")
    }
}
