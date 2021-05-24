package com.wsr

import com.wsr.installer.mainInstaller
import com.wsr.routings.mainRoute
import io.ktor.application.*
import org.koin.core.module.Module

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(testing: Boolean = false, testModule: Module? = null){

    //Installの設定
    mainInstaller(testing, testModule)

    //ルーティングの設定
    mainRoute()
}
