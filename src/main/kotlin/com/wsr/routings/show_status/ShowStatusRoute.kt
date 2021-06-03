package com.wsr.routings.show_status

import com.wsr.routings.show_status.home.homeRoute
import com.wsr.routings.show_status.introduction.introductionRoute
import com.wsr.routings.show_status.message.messageRoute
import io.ktor.http.content.*
import io.ktor.routing.*

/**
 * 管理者画面のルートを集める所
 */
fun Route.showStatusRoute(){
    homeRoute()
    introductionRoute()
    messageRoute()
}
