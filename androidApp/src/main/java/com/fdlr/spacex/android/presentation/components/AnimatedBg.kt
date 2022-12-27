package com.fdlr.spacex.android.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.fdlr.spaceX.android.R
import com.fdlr.spacex.android.presentation.utils.theme.bgGradientBlueBrush
import com.fdlr.spacex.android.presentation.utils.theme.bgGradientPurpleBrush

@Composable
fun AnimatedBg(
    speedAnimation: Int = 800,
    showBackground: Boolean = true,
    content: @Composable () -> Unit
) {
    if (showBackground) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if(isSystemInDarkTheme()) bgGradientBlueBrush else bgGradientPurpleBrush))
         {
            SuspendAnimation(speedAnimation)
            content()
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .focusable()
                .clickable(enabled = false) { } // no action with the screen
        ) {
            content()
        }
    }
}

@Composable
private fun SuspendAnimation(speedAnimation: Int) {
    val alpha = remember { mutableStateOf(1f) }
    LaunchedEffect(Unit) {
        animate(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(speedAnimation),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            alpha.value = value
        }
    }

    Image(
        painter = painterResource(R.drawable.bg),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                alpha = alpha.value
            )
    )
}
