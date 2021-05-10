package com.wsr.routings.show_status

import com.wsr.routings.show_status.home.homeRoute
import com.wsr.routings.show_status.introduction.introductionRoute
import com.wsr.routings.show_status.message.messageRoute
import io.ktor.routing.*

fun Routing.showStatusRoute(){
    homeRoute()
    introductionRoute()
    messageRoute()
}
