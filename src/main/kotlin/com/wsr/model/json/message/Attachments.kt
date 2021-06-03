package com.wsr.model.json.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachments(
    @SerialName("image_url") val imageUrl: String
)
