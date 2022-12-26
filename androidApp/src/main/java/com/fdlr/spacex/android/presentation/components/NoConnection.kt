package com.fdlr.spacex.android.presentation.components

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.*
import com.fdlr.spaceX.android.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoConnectionScreen() {
    Dialog(onDismissRequest = {}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        AnimatedBg(2000) {
            LottieForever()
        }
    }
}

@Composable
private fun LottieForever() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_connection))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CurvedText()

        LottieAnimation(
            composition,
            progress,
            modifier = Modifier
                .size(300.dp).padding(top= 54.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )

    }


}

@Composable
fun CurvedText() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
    ) {
        drawIntoCanvas {
            val textPadding = 48.dp.toPx()
            val arcHeight = 400.dp.toPx()
            val arcWidth = 300.dp.toPx()
            val path = Path().apply {
                addArc(0f, textPadding, arcWidth, arcHeight, 180f, 180f)
            }
            it.nativeCanvas.drawTextOnPath(
                "No Internet Connection",
                path,
                0f,
                0f,
                Paint().apply {
                    textSize = 20.sp.toPx()
                    color = Color.White.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
        }
    }

}