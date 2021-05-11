package com.wsr.model.json.i10jan

import kotlinx.serialization.Serializable

@Serializable
data class I10jan(
    val success: Boolean,
    val data: List<NickName>
)
