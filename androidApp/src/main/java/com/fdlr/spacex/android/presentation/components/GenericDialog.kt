package com.fdlr.spacex.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fdlr.domain.model.model.NegativeAction
import com.fdlr.domain.model.model.PositiveAction
import com.fdlr.spacex.android.presentation.utils.theme.SpaceXColors.Palette.grey

@Composable
fun GenericDialog(
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadFromQueue: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss?.invoke()
            onRemoveHeadFromQueue()
        },
        backgroundColor = grey,
        shape = RoundedCornerShape(22.dp),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )
        },
        text = {
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    color = Color.White
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                if (negativeAction != null) {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        onClick = {
                            negativeAction.onNegativeAction()
                            onRemoveHeadFromQueue()
                        }
                    ) {
                        Text(text = negativeAction.negativeBtnTxt)
                    }
                }
                if (positiveAction != null) {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        onClick = {
                            positiveAction.onPositiveAction()
                            onRemoveHeadFromQueue()
                        },
                    ) {
                        Text(text = positiveAction.positiveBtnTxt)
                    }
                }
            }
        }
    )
}