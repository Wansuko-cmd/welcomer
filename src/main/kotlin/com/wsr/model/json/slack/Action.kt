package com.wsr.model.json.slack

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Slackから送信されてくるJsonを読み取るためのクラス
 */
@Serializable
data class Action(
    val token: String,
    val event: Event,
    @SerialName("event_id") val eventId: String
)
