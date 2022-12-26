package com.fdlr.spacex.android.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fdlr.spaceX.android.R
import com.fdlr.spacex.android.presentation.components.AnimatedBg
import com.fdlr.spacex.android.presentation.utils.paddingEnd
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXTheme
import com.fdlr.spacex.datasource.network.model.NewDto
import com.fdlr.spacex.presentation.home.HomeEvents

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsView(
    loading: Boolean,
    page: Int,
    query: String,
    news: List<NewDto>,
    onTriggerEvent: (HomeEvents) -> Unit,
    onClickNewItem: (Int) -> Unit,
    showAboutTheApp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var showButton by remember { mutableStateOf(true) }


        Scaffold()
        {
            AnimatedBg(2000) {
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        SearchBar(
                            query = query,
                            onQueryChanged = {
                                showButton = it.isEmpty()
                                onTriggerEvent(HomeEvents.OnUpdateQuery(it))
                            },
                            onExecuteSearch = {
                                showButton = true
                                onTriggerEvent(HomeEvents.NewSearch(it))
                            },
                            reset = {
                                showButton = true
                                onTriggerEvent(HomeEvents.ResetSearch)
                            },
                            modifier = Modifier.weight(7f)
                        )
                        if (showButton) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.planet),
                                    contentDescription = "planet",
                                    modifier = Modifier
                                        .size(68.dp)
                                        .paddingEnd()
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null
                                        ) { showAboutTheApp() }
                                )
                            }

                        }
                    }

                    NewsList(
                        loading = loading,
                        news = news,
                        page = page,
                        onClickNewItem = {
                            onClickNewItem(it)
                            focusManager.clearFocus()
                        },
                        onTriggerNextPage = {
                            onTriggerEvent(HomeEvents.NextPage)
                        },
                        onRefresh = { onTriggerEvent(HomeEvents.LoadNews) })
                }
            }
        }
    }
