package com.wsr.model

import kotlinx.serialization.Serializable


//Jsonでメッセージを送信するためのクラス
@Serializable
data class Message(
    val text: String
)
