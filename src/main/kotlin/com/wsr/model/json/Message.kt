package com.wsr.model.json

import kotlinx.serialization.Serializable


//Jsonでメッセージを送信するためのクラス
@Serializable
data class Message(
    val text: String
)
