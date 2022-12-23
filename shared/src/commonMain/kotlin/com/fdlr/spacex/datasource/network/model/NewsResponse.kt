package com.fdlr.spacex.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    var results: List<NewDto>,
)