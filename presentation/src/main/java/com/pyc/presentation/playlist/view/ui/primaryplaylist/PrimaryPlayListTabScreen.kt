package com.pyc.presentation.playlist.view.ui.primaryplaylist

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pyc.presentation.R
import com.pyc.presentation.model.AudioPlaylistItemModel
import com.pyc.presentation.model.ColorModel
import com.pyc.presentation.playlist.view.ui.components.PlaylistScreenComponent


@Composable
internal fun PrimaryPlayListScreen(
    colorList: List<ColorModel>,
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
    onAddAudio: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onSearchAudioInList: (String) -> Unit
) {
    //임시
    val selectedColorId: Int? = 1

    PlaylistScreenComponent(
        topBarSlot = {
            ColorCategoryListRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                        vertical = dimensionResource(id = R.dimen.item_padding_vertical)
                    ),
                colorList,
                selectedColorId,
                onAllSelect = {},
                onColorSelect = {})
        },
        playlist = playlist,
        playlistId = playlistId,
        itemClick = itemClick,
        itemLongClick = itemLongClick,
        moreIconClick = moreIconClick,
        onAddAudio = onAddAudio,
        onEditPlaylist = onEditPlaylist,
        onSearchAudioInList = onSearchAudioInList


    )
}


@Composable
internal fun ColorCategoryListRow(
    modifier: Modifier,
    colorList: List<ColorModel>,
    selectedColorId: Int?,
    onAllSelect: () -> Unit,
    onColorSelect: (Int) -> Unit
) {
    Row(
        modifier = modifier,
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
                ColorCategoryItemColumn(selectedColorId, item = color)
            }
        }

    }
}


@Composable
internal fun ColorCategoryItemColumn(selectedColorId: Int?, item: ColorModel) {
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




