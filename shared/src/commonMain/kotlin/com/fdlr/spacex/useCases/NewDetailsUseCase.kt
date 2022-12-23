package com.fdlr.spacex.useCases

import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.model.UIComponentType
import com.fdlr.domain.model.util.CommonFlow
import com.fdlr.domain.model.util.DataState
import com.fdlr.domain.model.util.asCommonFlow
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.datasource.network.model.NewsResponse
import com.fdlr.spacex.datasource.network.services.SpaceXService
import kotlinx.coroutines.flow.flow

class NewDetailsUseCase(
    private val spaceX: SpaceXService
) {

    fun getNewDetail(id: Int): CommonFlow<DataState<NewDto>> = flow {
        try {
            emit(DataState.loading())
            val new = NewDto(
                id = id,
                title = "New spacecraft, new firsts, new hardware – International Space Station wraps up a busy and historic 2022",
                imageUrl = "https://www.nasaspaceflight.com/wp-content/uploads/2022/12/iss068e022435_large-2-1170x780.jpeg",
                newsSite = "NASASpaceflight",
                summary = "2022 has marked another busy chapter for the International Space Station (ISS). Along with its constant plethora of scientific and engineering experiments, the Station saw the first docking of Starliner, the all-private Axiom-1 mission, and new hardware installed to increase the lifespan of humanity’s collaborative space laboratory.",
                url = "https://www.nasaspaceflight.com/2022/12/iss-2022-roundup/",
            )
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