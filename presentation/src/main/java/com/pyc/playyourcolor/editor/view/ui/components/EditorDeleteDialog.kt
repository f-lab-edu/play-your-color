package com.pyc.playyourcolor.editor.view.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.pyc.playyourcolor.R

@Composable
fun EditorDeleteDialog(
    onDelete: () -> Unit,
    onCancel: () -> Unit,
) {
    EditorDialog(
        onDismissRequest = onCancel,
        messageField = {
           Text(
               text = stringResource(id = R.string.editor_delete_dialog_message),
               color = Color.White,
               fontSize = 20.sp
           )
        },
        buttonField = {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                ClickableText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        ) {
                            append(stringResource(id = R.string.cancel))
                        }
                    },
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                    onClick = { onCancel() }
                )
                ClickableText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontSize = 18.sp
                            )
                        ) {
                            append(stringResource(id = R.string.yes))
                        }
                    },
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                    onClick = { onDelete() }
                )
            }
        }
    )
}