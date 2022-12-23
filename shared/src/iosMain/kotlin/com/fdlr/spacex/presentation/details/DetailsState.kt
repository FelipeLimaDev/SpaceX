package com.fdlr.spacex.presentation.details

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.util.Queue
import com.fdlr.spacex.datasource.network.model.NewDto

actual data class DetailsState(
    val isLoading: Boolean = false,
    val new: NewDto,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
) {
    constructor() : this(
        isLoading = false,
        new = NewDto(
            id = 0,
            title = "",
            imageUrl = "",
            newsSite = "",
            summary = "",
            url = "",
        ),
        queue = Queue(mutableListOf()),
    )
}