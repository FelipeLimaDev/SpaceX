package com.fdlr.spacex.useCases

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.model.UIComponentType
import com.fdlr.domain.model.util.CommonFlow
import com.fdlr.domain.model.util.DataState
import com.fdlr.domain.model.util.asCommonFlow
import com.fdlr.spacex.datasource.cache.SpaceCache
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.datasource.network.model.NewsResponse
import com.fdlr.spacex.datasource.network.services.SpaceXService
import kotlinx.coroutines.flow.flow

class NewDetailsUseCase(
    private val spaceCache: SpaceCache
) {
    fun getNewDetail(id: Int): CommonFlow<DataState<NewDto>> = flow {
        try {
            emit(DataState.loading())
            val new = spaceCache.get(id)
            emit(DataState.data(message = null, data = new))
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
    }.asCommonFlow() as CommonFlow<DataState<NewDto>>
}