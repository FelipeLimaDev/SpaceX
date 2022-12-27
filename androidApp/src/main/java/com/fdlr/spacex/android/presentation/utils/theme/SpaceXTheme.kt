package com.fdlr.spacex.android.presentation.utils.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fdlr.domain.model.model.GenericMessageInfo
import com.fdlr.domain.model.util.Queue
import com.fdlr.spacex.android.presentation.components.ProcessDialogQueue
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.deepPurple500Dark
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientOne
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientThree
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val SpaceXLightThemeColors = lightColors(
    primary = SpaceXColors.Palette.deepPurple500,
    primaryVariant = SpaceXColors.Palette.deepPurple500Light,
    secondary = SpaceXColors.Palette.deepPurple500,
    secondaryVariant = SpaceXColors.Palette.deepPurple500Light,

    background = SpaceXColors.Palette.white,
    surface = SpaceXColors.Palette.white,
    error = SpaceXColors.Palette.red,

    onPrimary = SpaceXColors.Palette.white,
    onSecondary = SpaceXColors.Palette.white,
    onBackground = SpaceXColors.Palette.grey500,
    onSurface = SpaceXColors.Palette.grey500,
    onError = SpaceXColors.Palette.red
)

private val SpaceXDarkThemeColors = darkColors(
    primary = SpaceXColors.Palette.deepPurple500Dark,
    primaryVariant = SpaceXColors.Palette.deepPurple500Light,
    secondary = SpaceXColors.Palette.deepPurple500Dark,
    secondaryVariant = SpaceXColors.Palette.deepPurple500Light,

    background = SpaceXColors.Palette.white,
    surface = SpaceXColors.Palette.white,
    error = SpaceXColors.Palette.red,

    onPrimary = SpaceXColors.Palette.white,
    onSecondary = SpaceXColors.Palette.white,
    onBackground = SpaceXColors.Palette.grey500,
    onSurface = SpaceXColors.Palette.grey500,
    onError = SpaceXColors.Palette.red
)

@Composable
fun SpaceXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dialogQueue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
    onRemoveHeadMessageFromQueue: () -> Unit,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    MaterialTheme(
        colors = if (darkTheme) SpaceXDarkThemeColors else SpaceXLightThemeColors,
        typography = if (darkTheme) SpaceXDarkTypography else SpaceXLightTypography,
        shapes = SpaceXShapes,
        content = content
    )

    if(darkTheme){
        systemUiController.setStatusBarColor(
            color = gradientThree
        )
    }else{
        systemUiController.setStatusBarColor(
            color = gradientOne
        )
    }

    Box(Modifier.fillMaxSize()) {
        ProcessDialogQueue(
            dialogQueue = dialogQueue,
            onRemoveHeadMessageFromQueue = onRemoveHeadMessageFromQueue,
        )
    }
}