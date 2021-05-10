package com.wsr.routings.show_status.message

import com.wsr.model.Message
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

    post("/message"){
        val message = call.receive<Message>()
        SendMessageService.sendMessage(message)
    }
}
