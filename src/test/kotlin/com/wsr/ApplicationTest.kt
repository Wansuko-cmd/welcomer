package com.wsr

import com.wsr.entities.Challenge
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApplicationTest {
    @Test
    fun testRoot() {

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
