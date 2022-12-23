package com.fdlr.spacex.presentation.home

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.util.Queue
import com.fdlr.spacex.datasource.network.model.NewDto

actual data class HomeState(
    val isLoading: Boolean,
    val page: Int,
    val news: List<NewDto> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
) {
    constructor() : this(
        isLoading = false,
        page = 1,
        news = listOf(),
        queue = Queue(mutableListOf()),
    )

    companion object {
        const val NEWS_PAGINATION_PAGE_SIZE = 30
    }
}