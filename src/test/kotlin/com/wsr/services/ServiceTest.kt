package com.wsr.services

import com.wsr.model.db.DBController
import com.wsr.model.json.Message
import com.wsr.modules.I10janTestService
import com.wsr.services.i10jan.I10janInterface
import com.wsr.services.send_message.SendMessageInterface
import com.wsr.services.send_message.SendMessageService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ServiceTest : KoinComponent {


    @Before
    fun before(){
        val dbController = DBController().apply { init() }

        val testModule = module(override = true) {
            single<I10janInterface> { I10janTestService() }
            single<SendMessageInterface> { SendMessageService() }
            single { dbController }
        }

        startKoin { modules(testModule) }
    }

    @Test
    fun 部室に人がいる場合のI10janのメッセージ(){

        val sendMessageService by inject<SendMessageInterface>()
        val text = sendMessageService.makeReplyMessage("部室")
        Assert.assertEquals(Message("部室には今いけちぃとオキリョウがいますよ！"), text)
    }
}
