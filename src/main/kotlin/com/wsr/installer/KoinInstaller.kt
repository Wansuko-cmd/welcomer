package com.wsr.installer


import com.wsr.model.db.DBController
import com.wsr.services.i10jan.I10janInterface
import com.wsr.services.send_message.SendMessageService
import com.wsr.services.i10jan.I10janService
import com.wsr.services.send_message.SendMessageInterface
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger

/**
 * DIライブラリ「Koin」のインストール
 */
fun Application.koinInstaller(){

    //Applicationでは動かなかったため、初期設定したものをDI
    val dbController = DBController()
    dbController.init()

    install(Koin){
        slf4jLogger()
        modules(
            module {
                single<I10janInterface> { I10janService() }
                single<SendMessageInterface> { SendMessageService() }
                single { dbController }
            }
        )
    }
}
