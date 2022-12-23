package com.fdlr.spacex.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewDto(
    @SerialName("featured") val featured: Boolean,
    @SerialName("id") val id: Int,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("newsSite") val newsSite: String,
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("summary") val summary: String,
    @SerialName("title") val title: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("url") val url: String
)












