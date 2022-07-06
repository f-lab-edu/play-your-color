package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.model.ColorModel


@Composable
internal fun ColorListRow(
    colorList: List<ColorModel>,
    selectedColorId: Int?,
    onAllSelect: () -> Unit,
    onColorSelect: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextButton(
            onClick = {},
            modifier = Modifier
                .weight(0.2f)
        ) {
            val allSelected = (selectedColorId == null)
            Text(
                modifier = Modifier.padding(start = 0.dp, top = 10.dp, end = 5.dp, bottom = 10.dp),
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.all),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                    fontWeight = if (allSelected) FontWeight.Bold else FontWeight.Normal
                ),

                )
        }
        LazyRow(
            modifier = Modifier
                .weight(0.8f)
        ) {
            itemsIndexed(colorList) { index, color ->
                ColorItemColumn(selectedColorId, item = color)
            }
        }

    }
}


@Composable
internal fun ColorItemColumn(selectedColorId: Int?, item: ColorModel) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(item.color)
        ) {

            if (selectedColorId == item.id) {
                Icon(
                    modifier = Modifier.padding(all = 3.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if (item.color == Color.White) Color.Black else Color.White
                )
            }
        }
    }
}


@Preview
@Composable
internal fun PreviewColorListRow() {
    ColorListRow(
        colorList = listOf(
            ColorModel(1, "#ffee11", "오렌지"),
            ColorModel(2, "#faaa1d", "레드"),
            ColorModel(3, "#ddffaa", "블랙"),
            ColorModel(4, "#ccdd56", "갈색"),
            ColorModel(5, "#7a7aff", "화이트"),
            ColorModel(6, "#9d9d33", "노란색"),
        ),
        selectedColorId = 1,
        onAllSelect = {},
        onColorSelect = {}
    )
}