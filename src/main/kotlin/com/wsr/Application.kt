package com.wsr

import com.wsr.installer.mainInstaller
import com.wsr.model.db.DBController
import com.wsr.routings.mainRoute
import io.ktor.application.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    //Installの設定
    mainInstaller()

    //データベースの初期設定
    DBController.init()

    //ルーティングの設定
    mainRoute()
}
