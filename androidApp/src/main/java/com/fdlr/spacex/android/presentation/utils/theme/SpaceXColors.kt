package com.fdlr.spacex.android.presentation.utils.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientOne
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientTwo

object SpaceXColors {
    object Palette {
        val deepPurple500 = Color(0xFF9C27B0)
        val deepOrange500Light = Color(0xFF964CA3)
        val black = Color(0xFF000000)
        val white = Color(0xFFFFFFFF)
        val red = Color(0xFFF44336)
        val grey500 = Color(0xFF9E9E9E)
        val gradientOne = Color(0xFF23063E)
        val gradientTwo = Color(0xFFD9007B)
    }
}

val bgGradientPurpleBrush = Brush.verticalGradient(
    colors = listOf(
        gradientOne,
        gradientTwo
    )
)


