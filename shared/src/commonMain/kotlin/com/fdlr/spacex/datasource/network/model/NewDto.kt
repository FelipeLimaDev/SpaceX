package com.fdlr.spacex.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewDto(
    @SerialName("id") val id: Int,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("newsSite") val newsSite: String,
    @SerialName("summary") val summary: String,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String
)












