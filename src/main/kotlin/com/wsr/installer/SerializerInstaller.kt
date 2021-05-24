package com.wsr.installer

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

/**
 * シリアライザー「Kotlinx.serialization」のインストーラー
 */
fun Application.serializerInstaller() {
    //Serializerのインストール
    install(ContentNegotiation){
        json(Json {
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}
