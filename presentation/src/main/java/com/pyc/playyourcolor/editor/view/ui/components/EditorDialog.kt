package com.pyc.playyourcolor.editor.view.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun EditorDialog(
    onDismissRequest: () -> Unit,
    messageField: @Composable () -> Unit,
    buttonField: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .width(250.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(10.dp),
            color = Color.DarkGray
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 15.dp)
            ) {
                messageField()
                Spacer(Modifier.height(80.dp))
                buttonField()
            }
        }
    }
}