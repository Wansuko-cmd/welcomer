package com.wsr.model.json.i10jan

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NickName(
    @SerialName("nick_name")
    val nickName: String
)
