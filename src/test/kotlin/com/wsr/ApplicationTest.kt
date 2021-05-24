package com.wsr

import com.wsr.model.db.DBController
import com.wsr.model.json.Challenge
import com.wsr.model.json.i10jan.I10jan
import com.wsr.model.json.i10jan.NickName
import com.wsr.services.SendMessageService
import com.wsr.services.i10jan.I10janService
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApplicationTest{



    @BeforeTest
    fun before(){
        val dbController = DBController().apply { init() }

        val testModule = module(override = true) {
            single { I10janService() }
            single { SendMessageService() }
            single { dbController }
        }

        startKoin { listOf(testModule) }
    }

    /**
     * テストするURL: ルート
     * Method: Post
     * Receive: Challengeクラスと合致するJson
     * Response: 受け取ったものと同じJson
     */
    @Test
    fun testRootPost() {

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

    /**
     * テストするURL: ルート
     * Method: Get
     * Receive: なし
     * Response: This server is working!
     */
    @Test
    fun testRootGet(){
        withTestApplication({ main() }){
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("This server is working!", response.content)
            }
        }
    }

    @Test
    fun i10janTest(){

        val i10janJson = Json
            .encodeToString(I10jan(true, listOf(NickName("いけちぃ"), NickName("オキリョウ"))))

        withTestApplication({ main() }){
            handleRequest(HttpMethod.Post, "/test") {
                addHeader("Content-Type", "application/json")
                setBody(i10janJson)
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
