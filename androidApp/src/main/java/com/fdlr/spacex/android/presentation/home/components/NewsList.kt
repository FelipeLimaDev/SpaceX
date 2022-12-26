package com.fdlr.spacex.android.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fdlr.spacex.android.presentation.components.NewImage
import com.fdlr.spacex.android.presentation.utils.paddingHorzMedium
import com.fdlr.spacex.android.presentation.utils.paddingSmall
import com.fdlr.spacex.android.presentation.utils.paddingTopMedium
import com.fdlr.spacex.android.presentation.utils.paddingXXSmall
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXShapes
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.presentation.home.HomeEvents
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsList(
    loading: Boolean,
    news: List<NewDto>,
    onClickNewItem: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onRefresh: () -> Unit,
) {
    var refreshing by remember { mutableStateOf(false) }

    refreshing = loading

    SwipeRefresh(
        state = rememberSwipeRefreshState(loading),
        indicator = { _, _ -> },//remove the default indicator
        onRefresh = {
            refreshing = true
            onRefresh()
        },
    ) {
        LazyColumn(
            Modifier
                .paddingHorzMedium()
                .paddingTopMedium()
        ) {
            item {
                if (refreshing)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = Color.White
                        )
                        Text(
                            text = "Updating News...",
                            Modifier.paddingSmall(),
                            color = Color.White,
                            style = MaterialTheme.typography.caption
                        )
                    }
            }
            items(news.size) { index ->
                if ((index + 1) >= (page * 15) && !loading) {
                    onTriggerNextPage()
                }
                NewItem(
                    new = news[index],
                    onClickNewItem = onClickNewItem
                )
            }
            item {
                if (!loading && news.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No news available",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.paddingSmall()
                        )
                        Text(
                            text = "TRY AGAIN",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier
                                .paddingSmall()
                                .clickable {
                                    onRefresh()
                                })
                    }
                }
            }
        }
    }
}

@Composable
fun NewItem(
    new: NewDto,
    onClickNewItem: (Int) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)//clip ripple effect
            .clickable {
                onClickNewItem(new.id)
            }
    ) {

        Card(
            elevation = 8.dp,
            shape = SpaceXShapes.large
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp), contentAlignment = Alignment.BottomCenter
            ) {
                NewImage(
                    url = new.imageUrl, contentDescription = new.title, modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                Surface(
                    color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                    modifier = Modifier
                        .paddingXXSmall()
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = SpaceXShapes.large
                ) {
                    Text(
                        text = new.title,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.paddingSmall(),
                        color = Color.Black
                    )
                }
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
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
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}