package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.pyc.playyourcolor.R

@Composable
internal fun RoundedCornerShapeOutlinedButton(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_20dp)),
        border = BorderStroke(1.dp, Color.White),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent)

    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.caption.copy(color = Color.White)
        )
    }

}