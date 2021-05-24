package com.wsr.model.json.slack

import kotlinx.serialization.Serializable

/**
 * Slackから送られてきたActionのうちのEventを読み取るためのクラス
 */
@Serializable
data class Event(
    val type: String,
    val text: String? = null,
    val user: String? = null,
)
