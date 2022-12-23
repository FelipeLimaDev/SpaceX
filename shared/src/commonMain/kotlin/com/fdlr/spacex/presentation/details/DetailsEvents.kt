package com.fdlr.spacex.presentation.details

sealed class DetailsEvents {

    data class LoadNew(val id: Int) : DetailsEvents()

    data class OpenWebsite(val url: Boolean) : DetailsEvents()

    object OnRemoveHeadMessageFromQueue : DetailsEvents()

}








