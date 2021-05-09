package com.wsr.model.slack

import kotlinx.serialization.Serializable

//Slackから送信されてくるJsonを読み取るためのクラス
@Serializable
data class Slack(
    val token: String,
    val team_id: String,
    val api_app_id: String,
    val event: JoinTeam,
    val type: String,
    val event_id: String,
    val event_time: String,
    val authorizations: List<Authorization>,
    val is_ext_shared_channel: String,
    val event_context: String
)
