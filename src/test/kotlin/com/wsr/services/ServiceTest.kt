package com.wsr.services

import com.wsr.model.db.DBController
import com.wsr.modules.I10janTestService
import com.wsr.services.i10jan.I10janInterface
import com.wsr.services.i10jan.I10janService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.dsl.module

class ServiceTest : KoinComponent{


    @Before
    fun before(){
//        val dbController = DBController().apply { init() }
//
//        val testModule = module(override = true) {
//            single { I10janTestService() }
//            single { SendMessageService() }
//            single { dbController }
//        }
//
//        startKoin { listOf(testModule) }
    }

    @Test
    fun i10janTest(){
        val dbController = DBController().apply { init() }

        val testModule = module(override = true) {
            single { I10janTestService() }
            single { SendMessageService() }
            single { dbController }
        }

        startKoin {
            module(override = true) {
                single { I10janTestService() }
                single { SendMessageService() }
                single { dbController }
            }
        }

        val sendMessageService by inject<SendMessageService>()
        val text = sendMessageService.makeReplyMessage("部室")
        Assert.assertEquals("Hello World", text)
    }

    /**
     * アクション：Hello World
     * 返り値：Hello World
     */
//    @Test
//    fun helloTest(){
//        val text = SendMessageService.setMessage("Hello World").text
//        Assert.assertEquals("Hello World", text)
//    }
//
//    /**
//     * アクション：どれとも一致しない
//     * 返り値：Say again please?
//     */
//    @Test
//    fun elseTest(){
//        val text = SendMessageService.setMessage("Else").text
//        Assert.assertEquals("Say again please?", text)
//    }
}
