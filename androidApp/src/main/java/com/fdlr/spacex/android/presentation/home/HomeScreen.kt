package com.fdlr.spacex.android.presentation.home

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.fdlr.spacex.android.presentation.components.AnimatedBg
import com.fdlr.spacex.android.presentation.components.CustomAboutDialog
import com.fdlr.spacex.android.presentation.components.LoadingState
import com.fdlr.spacex.android.presentation.components.NoConnectionScreen
import com.fdlr.spacex.android.presentation.destinations.DetailsScreenDestination
import com.fdlr.spacex.android.presentation.home.components.NewsView
import com.fdlr.spacex.android.presentation.utils.ConnectivityObserver
import com.fdlr.spacex.android.presentation.utils.NetworkConnectivityObserver
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXTheme
import com.fdlr.spacex.presentation.home.HomeEvents
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
    lateinit var connectivityObserver: ConnectivityObserver

    val state = viewModel.state.value

    var isCustomAboutDialog by remember { mutableStateOf(false) }
    val applicationContext = LocalContext.current.applicationContext
    connectivityObserver = NetworkConnectivityObserver(applicationContext)

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Available
    )

    SpaceXTheme(
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            viewModel.onTriggerEvent(HomeEvents.OnRemoveHeadMessageFromQueue)
        }

    ) {
        AnimatedBg {
            NewsView(
                loading = state.isLoading,
                news = state.news,
                query = state.query,
                page = state.page,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickNewItem = { id ->
                    navigator.navigate(DetailsScreenDestination(id = id))
                },
                showAboutTheApp = { isCustomAboutDialog = true })

            if (state.isLoading) {
                LoadingState()
            }

            CustomAboutDialog(
                showDialog = isCustomAboutDialog,
                onDismissRequest = {
                    isCustomAboutDialog = false
                }
            )

            if (status == ConnectivityObserver.Status.Unavailable || status == ConnectivityObserver.Status.Lost || status == ConnectivityObserver.Status.Losing) {
                NoConnectionScreen()
            }
        }
    }
}