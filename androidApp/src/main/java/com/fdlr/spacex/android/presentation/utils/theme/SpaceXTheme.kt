package com.fdlr.spacex.android.presentation.utils.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val SpaceXLightThemeColors = lightColors(
    primary = SpaceXColors.Palette.deepPurple500,
    primaryVariant = SpaceXColors.Palette.deepOrange500Light,
    secondary = SpaceXColors.Palette.deepPurple500,
    secondaryVariant = SpaceXColors.Palette.deepOrange500Light,

    background = SpaceXColors.Palette.white,
    surface = SpaceXColors.Palette.white,
    error = SpaceXColors.Palette.red,

    onPrimary = SpaceXColors.Palette.white,
    onSecondary = SpaceXColors.Palette.white,
    onBackground = SpaceXColors.Palette.grey500,
    onSurface = SpaceXColors.Palette.grey500,
    onError = SpaceXColors.Palette.red
)

private val SpaceXDarkThemeColors = SpaceXLightThemeColors

@Composable
fun SpaceXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) SpaceXDarkThemeColors else SpaceXLightThemeColors,
        typography = if (darkTheme) SpaceXDarkTypography else SpaceXLightTypography,
        shapes = SpaceXShapes,
        content = content
    )
}