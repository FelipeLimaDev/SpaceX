package com.fdlr.spacex.android.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.fdlr.spaceX.android.R


@Composable
fun LoadingState() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.space))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    AnimatedBg(showBackground = false) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}