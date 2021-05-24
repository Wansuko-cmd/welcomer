package com.wsr.model.json

import kotlinx.serialization.Serializable


/**
 * Jsonでメッセージを送信する際に利用するクラス
 */
@Serializable
data class Message(
    val text: String
)
