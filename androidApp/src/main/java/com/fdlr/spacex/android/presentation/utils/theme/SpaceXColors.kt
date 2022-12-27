package com.fdlr.spacex.android.presentation.utils.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientFour
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientOne
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientThree
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.gradientTwo

object SpaceXColors {
    object Palette {
        val deepPurple500 = Color(0xFF9C27B0)
        val deepPurple500Dark = Color(0xFF0A69FF)

        val deepPurple500Light = Color(0xFF964CA3)
        val black = Color(0xFF000000)
        val grey = Color(0xFF2B2B2B)
        val white = Color(0xFFFFFFFF)
        val red = Color(0xFFF44336)
        val grey500 = Color(0xFF9E9E9E)

        val gradientOne = Color(0xFF23063E)
        val gradientTwo = Color(0xFFD9007B)

        val gradientThree = Color(0xFF0041A8)
        val gradientFour= Color(0xFF00C6FB)
    }
}

val bgGradientPurpleBrush = Brush.verticalGradient(
    colors = listOf(
        gradientOne,
        gradientTwo
    )
)

val bgGradientBlueBrush = Brush.verticalGradient(
    colors = listOf(
        gradientThree,
        gradientFour
    )
)


