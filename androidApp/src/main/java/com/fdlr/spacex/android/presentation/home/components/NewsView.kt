package com.fdlr.spacex.android.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import com.fdlr.spacex.android.presentation.components.AnimatedBg
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
    onClickNewItem: (Int) -> Unit
) {
    val focusManager = LocalFocusManager.current

    SpaceXTheme {
        Scaffold()
        {
            AnimatedBg(2000) {
                Column() {
                    SearchBar(
                        query = query,
                        onQueryChanged = {
                            onTriggerEvent(HomeEvents.OnUpdateQuery(it))
                        },
                        onExecuteSearch = {
                            onTriggerEvent(HomeEvents.NewSearch(it))
                        },
                        reset = {
                            onTriggerEvent(HomeEvents.ResetSearch)
                        }
                    )
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
                        })
                }
            }
        }
    }
}