package com.fdlr.spacex.android.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fdlr.spacex.android.presentation.components.AnimatedBg
import com.fdlr.spacex.android.presentation.components.NewImage
import com.fdlr.spacex.android.presentation.utils.*
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXShapes
import com.fdlr.spacex.datasource.network.model.NewDto

@Composable
fun NewsList(
    loading: Boolean,
    news: List<NewDto>,
    onClickNewItem: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
) {
        LazyColumn(
            Modifier
                .paddingHorzMedium()
                .paddingTopMedium()) {
            items(news.size) { index ->
                if ((index + 1) >= (page * 15) && !loading) {
                    onTriggerNextPage()
                }
                NewItem(
                    new = news[index],
                    onClickNewItem = onClickNewItem
                )
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
                        modifier = Modifier.paddingSmall()
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