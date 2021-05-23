package com.wsr.installer


import com.wsr.model.db.DBController
import com.wsr.services.SendMessageService
import com.wsr.services.i10jan.I10janService
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun Application.koinInstaller(){

    //Applicationでは動かなかったため、初期設定したものをDI
    val dbController = DBController()
    dbController.init()

    install(Koin){
        module {
            single { I10janService() }
            single { SendMessageService() }
            single { dbController }
        }
    }
}
