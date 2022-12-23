package com.fdlr.spacex.presentation.home

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.util.Queue
import com.fdlr.spacex.datasource.network.model.NewDto

actual data class HomeState(
    val isLoading: Boolean = false,
    val query: String = "",
    val page: Int = 1,
    val news: List<NewDto> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
) {
    constructor() : this(
        isLoading = false,
        query = "",
        page = 1,
        news = listOf(),
        queue = Queue(mutableListOf()),
    )
}