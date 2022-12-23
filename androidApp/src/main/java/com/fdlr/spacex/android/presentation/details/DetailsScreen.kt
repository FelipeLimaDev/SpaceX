package com.fdlr.spacex.android.presentation.details

import androidx.compose.runtime.Composable
import com.fdlr.spacex.android.presentation.details.components.DetailView
import com.fdlr.spacex.presentation.details.DetailsEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun DetailsScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val state = viewModel.state.value

    DetailView(
        new = state.new,
        isLoading = state.isLoading,
        onOpenWebsite = { open ->
//            viewModel.onTriggerEvent(DetailsEvents.OnOpenWebsite(open))
        },
        onRemoveHeadMessageFromQueue = {
            viewModel.onTriggerEvent(DetailsEvents.OnRemoveHeadMessageFromQueue)
        },
        onNavigateUp = {
            navigator.navigateUp()
        }
    )

    viewModel.onTriggerEvent(DetailsEvents.LoadNew(id))

}


