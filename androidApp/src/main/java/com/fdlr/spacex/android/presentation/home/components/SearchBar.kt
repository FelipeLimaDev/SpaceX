package com.fdlr.spacex.android.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdlr.spacex.android.presentation.utils.paddingHorzMedium
import com.fdlr.spacex.android.presentation.utils.paddingTopSmall

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: (String) -> Unit,
    reset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                onQueryChanged("")
                reset()
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }

    Box(
        modifier
            .fillMaxWidth()
            .paddingHorzMedium()
            .paddingTopSmall()
            .height(60.dp)
            .clip(MaterialTheme.shapes.large)
            .background(Color.White.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {

        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = query,
            onValueChange = { onQueryChanged(it) },
            textStyle = TextStyle(
                fontSize = 20.sp, color = Color.Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            singleLine = true,
            trailingIcon = if (query.isNotBlank()) trailingIconView else null,
            keyboardActions = KeyboardActions(
                onDone = {
                    if (query.isNotBlank() && query.length > 2) {
                        onExecuteSearch(query)
                    } else {
                        reset()
                    }
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                textColor = Color.Black,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Black,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        if (query.isBlank()) {
            Text(
                text = "Search...",
                style = TextStyle(
                    fontSize = 20.sp, color = Color.Black.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp)
            )
        }
    }


}


