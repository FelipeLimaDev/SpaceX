package com.fdlr.spacex.android.presentation.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.model.UIComponentType
import com.fdlr.domain.model.util.GenericMessageInfoQueueUtil
import com.fdlr.domain.model.util.Queue
import com.fdlr.domain.model.util.asCommonFlow
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.presentation.details.DetailsEvents
import com.fdlr.spacex.presentation.details.DetailsState
import com.fdlr.spacex.useCases.UseCases
import java.util.*

class DetailsViewModel(
    private val useCases: UseCases
) : ViewModel() {

    val state: MutableState<DetailsState> = mutableStateOf(DetailsState())


    fun onTriggerEvent(event: DetailsEvents) {
        when (event) {
            is DetailsEvents.LoadNew -> getNew(event.id)
            is DetailsEvents.OnRemoveHeadMessageFromQueue -> removeHeadMessage()
            is DetailsEvents.OpenWebsite -> openWebsite(event.url)
            else -> {
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("News not found.")
                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

    private fun openWebsite(url: Boolean) {
        state.value = state.value.copy(url = url)
    }

    private fun getNew(id: Int) {
        useCases.newDetailsUseCase.getNewDetail(id = id).collectCommon { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { new ->
                    state.value = state.value.copy(new = new)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }
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

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value = state.value.copy(queue = queue)
        } catch (_: Exception) {
        }
    }
}