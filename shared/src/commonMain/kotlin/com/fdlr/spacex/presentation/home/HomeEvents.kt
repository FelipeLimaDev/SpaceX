package com.fdlr.spacex.presentation.home

sealed class HomeEvents{

    object LoadNews: HomeEvents()

    object NextPage: HomeEvents()

    object OnRemoveHeadMessageFromQueue: HomeEvents()

    object ResetSearch: HomeEvents()

    data class OnUpdateQuery(val query: String): HomeEvents()

    data class NewSearch(val query: String): HomeEvents()
}








