package com.wsr.routings

import com.wsr.routings.reply_message.replyMessageRoute
import com.wsr.routings.show_status.showStatusRoute
import io.ktor.application.*
import io.ktor.routing.*

fun Application.mainRoute(){
    routing {
        replyMessageRoute()
        showStatusRoute()
    }
}
