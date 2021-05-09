package com.wsr.model.slack

import kotlinx.serialization.Serializable

@Serializable
data class JoinTeam(
    val type: String,
    val user: String,
    val channel: String,
    val channel_type: String,
    val team: String,
    val event_ts: String
)
