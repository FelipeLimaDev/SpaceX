package com.fdlr.spacex.useCases

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.model.UIComponentType
import com.fdlr.domain.model.util.CommonFlow
import com.fdlr.domain.model.util.DataState
import com.fdlr.domain.model.util.asCommonFlow
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.datasource.network.model.NewsResponse
import com.fdlr.spacex.datasource.network.services.SpaceXService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HomeNewsUseCase(
    private val spaceX: SpaceXService
) {
    private var savedNews: List<NewDto> = emptyList()
    fun getNews(
        limit: Int,
        start: Int,
    ): CommonFlow<DataState<List<NewDto>>> = flow {
        try {
            emit(DataState.loading())
            delay(5000)
            val news = spaceX.getNews(limit, start)
            emit(DataState.data(message = null, data = news))
            savedNews = news
        } catch (e: Exception) {
            emit(
                DataState.error<NewsResponse>(
                    message = GenericMessageInfo.Builder()
                        .id("HomeNewsUseCase.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                )
            )
        }
    }.asCommonFlow() as CommonFlow<DataState<List<NewDto>>>


    fun getNewsFromCache(): CommonFlow<DataState<List<NewDto>>> = flow {
        emit(DataState.data(message = null, data = savedNews))
    }.asCommonFlow()


    fun filterNewsByQuery(query: String): CommonFlow<DataState<List<NewDto>>> = flow {
        if (query.isBlank()) {
            emit(DataState.data(message = null, data = savedNews))
        } else {
            val filteredNews = savedNews.filter { new ->
                new.title.contains(query, true) || new.summary.contains(query, true)
            }
            emit(DataState.data(message = null, data = filteredNews))
        }
    }.asCommonFlow()
}





