package com.fdlr.spacex.android.presentation.home

import androidx.compose.runtime.Composable
import com.fdlr.spacex.android.presentation.components.AnimatedBg
import com.fdlr.spacex.android.presentation.components.LoadingState
import com.fdlr.spacex.android.presentation.destinations.DetailsScreenDestination
import com.fdlr.spacex.android.presentation.home.components.NewsView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.state.value

    AnimatedBg {
        NewsView(
            loading = state.isLoading,
            news = state.news,
            query = state.query,
            page = state.page,
            onTriggerEvent = viewModel::onTriggerEvent,
            onClickNewItem = { id ->
                navigator.navigate(DetailsScreenDestination(id = id))
            })

        if (state.isLoading) {
            LoadingState()
        }
    }

}