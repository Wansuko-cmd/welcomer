package com.wsr.model.json.slack

import kotlinx.serialization.Serializable

//Slackから送信されてくるJsonを読み取るためのクラス
@Serializable
data class Action(
    val token: String,
    val event: Event,
    val event_id: String
)
