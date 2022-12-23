package com.fdlr.spacex.presentation.details

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.util.Queue
import com.fdlr.spacex.datasource.network.model.NewDto

actual data class DetailsState(
    val isLoading: Boolean = false,
    val url : Boolean = false,
    val new: NewDto? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
) {
    constructor() : this(
        isLoading = false,
        url = false,
        new = null,
        queue = Queue(mutableListOf())
    )
}