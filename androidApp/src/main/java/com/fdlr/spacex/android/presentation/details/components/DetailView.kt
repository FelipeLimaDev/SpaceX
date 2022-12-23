package com.fdlr.spacex.android.presentation.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdlr.spacex.android.presentation.components.AnimatedBg
import com.fdlr.spacex.android.presentation.components.LoadingState
import com.fdlr.spacex.android.presentation.components.NewImage
import com.fdlr.spacex.android.presentation.utils.paddingMedium
import com.fdlr.spacex.android.presentation.utils.paddingSmall
import com.fdlr.spacex.android.presentation.utils.paddingXXSmall
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXShapes
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXTheme
import com.fdlr.spacex.datasource.network.model.NewDto

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailView(
    new: NewDto?,
    isLoading: Boolean,
    onOpenWebsite: (Boolean) -> Unit,
    onRemoveHeadMessageFromQueue: () -> Unit,
    onNavigateUp: () -> Unit
) {
    val uriHandler = LocalUriHandler.current
    SpaceXTheme {
        Scaffold()
        {
            AnimatedBg(2000) {
                if (new != null) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .paddingMedium()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.7f))
                        ) {
                            IconButton(onClick = { onNavigateUp() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            Modifier
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.large)
                                .verticalScroll(rememberScrollState())
                                .background(Color.Black.copy(0.7f))
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
                                Box(
                                    Modifier
                                        .clip(MaterialTheme.shapes.large),
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    NewImage(
                                        url = new.imageUrl,
                                        contentDescription = new.title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(330.dp)
                                    )
                                    if (!new.url.isNullOrBlank()) {
                                        Surface(
                                            color = Color.White.copy(alpha = 0.7f),
                                            modifier = Modifier
                                                .paddingXXSmall()
                                                .wrapContentWidth()
                                                .wrapContentHeight()
                                                .clickable { uriHandler.openUri(new.url) },
                                            shape = SpaceXShapes.large
                                        ) {
                                            Text(
                                                text = "Open website",
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 16.sp
                                                ),
                                                modifier = Modifier.paddingSmall()
                                            )
                                        }
                                    }
                                }
                                if (!new.newsSite.isNullOrBlank()) {
                                    Surface(
                                        color = Color.Black.copy(alpha = 0.7f),
                                        modifier = Modifier
                                            .paddingXXSmall()
                                            .wrapContentWidth()
                                            .wrapContentHeight(),
                                        shape = SpaceXShapes.large
                                    ) {
                                        Text(
                                            text = new.newsSite.uppercase(),
                                            style = MaterialTheme.typography.body1,
                                            modifier = Modifier.paddingSmall(),
                                            color = Color.White
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = new.title,
                                style = MaterialTheme.typography.h5,
                                color = Color.White,
                                modifier = Modifier.paddingSmall()
                            )
                            Text(
                                text = new.summary,
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                                modifier = Modifier.paddingSmall()
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                    }
                }
            }

            if (isLoading)
                LoadingState()

        }
    }
}
