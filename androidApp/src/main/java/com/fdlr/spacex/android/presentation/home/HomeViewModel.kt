package com.fdlr.spacex.android.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.model.UIComponentType
import com.fdlr.domain.model.util.GenericMessageInfoQueueUtil
import com.fdlr.domain.model.util.Queue
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.presentation.home.HomeEvents
import com.fdlr.spacex.presentation.home.HomeState
import com.fdlr.spacex.presentation.home.NEWS_PAGINATION_PAGE_SIZE
import com.fdlr.spacex.useCases.UseCases
import java.util.*

class HomeViewModel(
    private val useCases: UseCases
) : ViewModel() {

    val state: MutableState<HomeState> = mutableStateOf(HomeState())

    init {
        getNews()
    }

    fun onTriggerEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.LoadNews -> {
                getNews()
            }
            is HomeEvents.NewSearch -> {
                newSearch(event.query)
            }
            is HomeEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, news = listOf())
                newSearch(event.query)
            }
            is HomeEvents.NextPage -> {
                nextPage()
            }
            is HomeEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            is HomeEvents.ResetSearch -> {
                getNewsFromCache()
            }
            else -> {
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Something went wrong.")
                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        getNews()
    }

    private fun newSearch(query: String = "") {
        state.value = state.value.copy(page = 1, news = listOf())
        useCases.homeNewsUseCase.filterNewsByQuery(query)
            .collectCommon(viewModelScope) { dataState ->
                dataState.data?.let { news ->
                    appendNews(news)
                }
            }
    }

    private fun getNewsFromCache() {
        useCases.homeNewsUseCase.getNewsFromCache().collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { news ->
                appendNews(news)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }
    }

    private fun getNews() {
        useCases.homeNewsUseCase.getNews(
            limit = state.value.page * NEWS_PAGINATION_PAGE_SIZE,
            start = if (state.value.page > 1) (state.value.page - 1) * NEWS_PAGINATION_PAGE_SIZE else 0,
        ).collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { news ->
                appendNews(news)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value = state.value.copy(queue = queue)
        } catch (_: Exception) {
        }
    }

    private fun appendNews(news: List<NewDto>) {
        val current = ArrayList(state.value.news)
        current.addAll(news)
        state.value = state.value.copy(news = current)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil()
                .doesMessageAlreadyExistInQueue(
                    queue = state.value.queue,
                    messageInfo = messageInfo.build()
                )
        ) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }
}