package com.wsr.routings.show_status.message

import com.wsr.model.json.Message
import com.wsr.model.db.DBController
import com.wsr.services.SendMessageService
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.messageRoute(){
    get("/message"){
        call.respond(FreeMarkerContent("views/pages/message/index.ftl", mapOf("" to ""), ""))
    }

    get("/message/done"){
        call.respond(FreeMarkerContent("views/pages/message/done.ftl", mapOf("" to ""), ""))
    }

    post("/message"){
        val params = call.receiveParameters()

        params["body"]?.let {
            SendMessageService.sendMessage(Message(it))
            DBController.makeSentMessageHistory("Owner", "Webから送信", it)
        }

        call.respondRedirect("/message/done")
    }
}
