package com.fdlr.spacex.android.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailsScreen(
    id: Int,
    navigator: DestinationsNavigator,
) {
    Box(Modifier.fillMaxSize().background(Color.Green)) {

    }

}