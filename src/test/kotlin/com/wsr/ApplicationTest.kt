package com.wsr

import com.wsr.model.db.DBController
import com.wsr.model.json.Challenge
import com.wsr.services.i10jan.I10janInterface
import com.wsr.services.send_message.SendMessageService
import com.wsr.services.i10jan.I10janService
import com.wsr.services.send_message.SendMessageInterface
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApplicationTest{

//    @BeforeTest
//    fun before(){
//        val dbController = DBController().apply { init() }
//
//        val testModule = module(override = true) {
//            single<I10janInterface> { I10janService() }
//            single<SendMessageInterface> { SendMessageService() }
//            single { dbController }
//        }
//
//        startKoin { modules(testModule) }
//    }

    /**
     * テストするURL: ルート
     * Method: Post
     * Receive: Challengeクラスと合致するJson
     * Response: 受け取ったものと同じJson
     */
    @Test
    fun Slackに登録する際に飛ばされるChallengeを返す処理() {

        val challengeJson = Json.encodeToString(
            Challenge(
                "Jhj5dZrVaK7ZwHHjRyZWjbDl",
                "3eZbrw1aBm2rZgRNFdxV2595E9CY3gmdALWMmHkvFXO7tYXAYM8P",
                "url_verification"
            )
        )

        withTestApplication({ main() }) {
            handleRequest(HttpMethod.Post, "/") {
                addHeader("Content-Type", "application/json")
                setBody(challengeJson)
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(challengeJson, response.content)
            }
        }
    }
}
