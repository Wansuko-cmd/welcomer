package com.wsr.routings

import com.wsr.routings.api.apiRoute
import com.wsr.routings.reply_message.replyMessageRoute
import com.wsr.routings.show_status.showStatusRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.content.*
import io.ktor.routing.*

/**
 * 全体のルートを集めるところ
 */
fun Application.mainRoute(){
    routing {
        authenticate("auth-basic") {
            apiRoute()
            showStatusRoute()
        }
        replyMessageRoute()

        /**
         * 画像ファイルを配信するルート
         */
        static("/src"){
            resources("src")
        }
    }
}
