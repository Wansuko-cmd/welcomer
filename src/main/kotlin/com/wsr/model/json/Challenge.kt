package com.wsr.model.json

import kotlinx.serialization.*

/**
 * SlackにUrlを登録する際に使うクラス
 */
@Serializable
data class Challenge(
    val token: String,
    val challenge: String,
    val type: String
)
