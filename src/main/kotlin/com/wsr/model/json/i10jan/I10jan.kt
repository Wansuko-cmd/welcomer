package com.wsr.model.json.i10jan

import kotlinx.serialization.Serializable

/**
 * I10janにアクセスした際の返り値のJsonを受け取るためのクラス
 */
@Serializable
data class I10jan(
    val success: Boolean,
    val data: List<NickName>
)
