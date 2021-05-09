package com.wsr.model

import kotlinx.serialization.*

//Urlを登録する際に使うクラス
@Serializable
data class Challenge(
    val token: String,
    val challenge: String,
    val type: String
)
