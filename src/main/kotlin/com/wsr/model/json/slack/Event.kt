package com.wsr.model.json.slack

import com.typesafe.config.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val type: String,
    val text: String? = null,
    val user: String? = null,
)