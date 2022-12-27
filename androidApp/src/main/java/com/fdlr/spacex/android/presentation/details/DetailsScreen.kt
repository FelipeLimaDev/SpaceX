package com.fdlr.spacex.android.presentation.details

import androidx.compose.runtime.Composable
import com.fdlr.spacex.android.presentation.details.components.DetailView
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXTheme
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

    SpaceXTheme(
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            viewModel.onTriggerEvent(DetailsEvents.OnRemoveHeadMessageFromQueue)
        }
    ) {
        DetailView(
            new = state.new,
            isLoading = state.isLoading,
            onNavigateUp = {
                navigator.navigateUp()
            }
        )
        viewModel.onTriggerEvent(DetailsEvents.LoadNew(id))
    }
}


