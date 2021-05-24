package com.wsr.model.json.i10jan

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * I10janクラスで使用されている「NickName」を定義
 */
@Serializable
data class NickName(
    @SerialName("nick_name")
    val nickName: String
)
