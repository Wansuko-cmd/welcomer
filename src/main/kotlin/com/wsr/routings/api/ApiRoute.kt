package com.wsr.routings.api

import com.wsr.model.h2.entities.SentMessage
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.apiRoute(){
    get("/api/message/dayToCount"){

        var sentMessages = listOf<SentMessage>()


        //データベースに登録されているユーザーを抽出
        transaction {
            sentMessages = SentMessage.all().toList()
        }

        val messageGroupByDay = sentMessages
            .groupBy { it.createdAt.toLocalDateTime().dayOfMonth }

        val dayToCount = messageGroupByDay.map { mapOf(it.key to it.value.size) }

        call.respond(dayToCount)
    }
}
