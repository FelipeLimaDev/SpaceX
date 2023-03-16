package com.fdlr.spacex.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fdlr.spaceX.android.R
import com.fdlr.spacex.android.presentation.utils.paddingMedium
import com.fdlr.spacex.android.presentation.utils.paddingStart
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.grey

@Composable
fun CustomAboutDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    var isYounger by remember { mutableStateOf(true) }

    if (showDialog) {
        isYounger = true
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)),
                content = {
                    Column(
                        modifier = Modifier
                            .background(grey)
                            .clip(MaterialTheme.shapes.large)
                    ) {

                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .paddingMedium(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButton(onClick = { onDismissRequest() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                            Text(
                                modifier = Modifier.paddingStart(),
                                text = "Hey there!",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = if (isYounger) R.drawable.ic_me_younger else R.drawable.ic_me_old),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .size(100.dp)
                                    .shadow(elevation = 10.dp)
                                    .clickable { isYounger = !isYounger }
                            )
                            Text(
                                modifier = Modifier.padding(top = 15.dp),
                                text = "Developed by",
                                style = MaterialTheme.typography.subtitle1,
                                color = Color.White
                            )

                            Text(
                                text = "Felipe Rodrigues",
                                style = MaterialTheme.typography.subtitle1,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .paddingMedium(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_github),
                                        contentDescription = null,
                                        modifier = Modifier.clickable { uriHandler.openUri("https://github.com/Fdlr/") }
                                    )
                                    ClickableText(
                                        modifier = Modifier.padding(top = 15.dp),
                                        text = AnnotatedString(text = "GitHub"),
                                        style = TextStyle(color = Color.White, fontSize = 14.sp),
                                        onClick = {
                                            uriHandler.openUri("https://github.com/Fdlr/SpaceX")
                                        }
                                    )
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_instagram),
                                        contentDescription = null,
                                        modifier = Modifier.clickable { uriHandler.openUri("https://www.instagram.com/fdlr.eng/?hl=en") }
                                    )
                                    ClickableText(
                                        modifier = Modifier.padding(top = 15.dp),
                                        style = TextStyle(color = Color.White, fontSize = 14.sp),
                                        text = AnnotatedString(text = "Instagram"),
                                        onClick = {
                                            uriHandler.openUri("https://www.instagram.com/fdlr.eng/?hl=en")
                                        }
                                    )
                                }
                            }

                        }

                    }
                })
        }
    }
}