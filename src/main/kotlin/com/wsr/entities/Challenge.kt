package com.wsr.entities

import kotlinx.serialization.*

@Serializable
data class Challenge(
    val token: String,
    val challenge: String,
    val type: String
)
