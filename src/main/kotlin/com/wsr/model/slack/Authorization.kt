package com.wsr.model.slack

import kotlinx.serialization.Serializable

@Serializable
data class Authorization(
    val enterprise_id: String?,
    val team_id: String,
    val user_id: String,
    val is_bot: Boolean,
    val is_enterprise_install: Boolean
)
