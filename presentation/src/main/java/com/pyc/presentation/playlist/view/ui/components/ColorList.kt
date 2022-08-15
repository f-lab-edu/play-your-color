package com.pyc.presentation.playlist.view.ui.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyc.presentation.model.ColorListItemModel
import com.pyc.presentation.R
import com.pyc.presentation.model.ColorModel

@Composable
internal fun ColorPlayListTabColorList(
    colorList: List<ColorListItemModel>,
    colorClickListener: (ColorModel) -> Unit
) {
    Column() {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.item_padding_vertical)),
            text = stringResource(id = R.string.color_list_select),
            style = MaterialTheme.typography.body1.copy(color = Color.White),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.item_padding_vertical)
                ),
            text = stringResource(id = R.string.color_list_count, colorList.size),
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )


        LazyColumn(Modifier.fillMaxWidth()) {
            itemsIndexed(colorList) { position, item ->
                val coloInfo = item.colorInfo

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable { colorClickListener(coloInfo) }
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                            vertical = dimensionResource(id = R.dimen.item_padding_vertical)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(75.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(coloInfo.color)
                    )
                    Spacer(Modifier.width(20.dp))
                    Column(modifier = Modifier.wrapContentSize()) {
                        Text(
                            modifier = Modifier.wrapContentSize(),
                            text = coloInfo.name,
                            style = MaterialTheme.typography.subtitle1.copy(Color.White)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            modifier = Modifier.wrapContentSize(),
                            text = stringResource(id = R.string.audio_count, item.audioCount),
                            style = MaterialTheme.typography.caption.copy(Color.Gray)
                        )

                    }
                }

            }
        }

    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = false)
@Composable
internal fun PreviewColorPlayListTabColorList() {
    val colorList = listOf<ColorListItemModel>(
        ColorListItemModel(ColorModel(1, "#ffee11", "오렌지"), 10),
        ColorListItemModel(ColorModel(2, "#faaa1d", "레드"), 1),
        ColorListItemModel(ColorModel(3, "#ddffaa", "블랙"), 20),
        ColorListItemModel(ColorModel(4, "#ccdd56", "갈색"), 51),
        ColorListItemModel(ColorModel(5, "#7a7aff", "화이트"), 18),
        ColorListItemModel(ColorModel(6, "#9d9d33", "노란색"), 33),
    )
    ColorPlayListTabColorList(colorList, {})
}