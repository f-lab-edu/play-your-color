package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.extensions.toFormattedDuration
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.playlist.view.ui.theme.PrimaryColor
import com.skydoves.landscapist.glide.GlideImage


@Composable
internal fun PlaylistRow(
    playlist: List<AudioPlaylistItemModel>,
    nowPlayingAudioId: Int?,
    itemClick: (Int, Boolean) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(playlist) { index, item ->
            PlaylistItemRow(
                nowPlayingAudioId == index, item, itemClick, itemLongClick, moreIconClick
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PlaylistItemRow(
    nowPlaying: Boolean = false,
    item: AudioPlaylistItemModel,
    itemClick: (Int, Boolean) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit
) {
    val audio = item.audio
    Box(Modifier.wrapContentSize()) {

        var itemColor = Color.White

        if (!item.audio.playPossible) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RectangleShape)
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
            itemColor = Color.Gray
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    Color(
                        1f,
                        1f,
                        1f,
                        if (nowPlaying) 0.3f else 0f
                    )
                )
                .combinedClickable(
                    onClick = { itemClick(item.id, item.audio.playPossible) },
                    onLongClick = { itemLongClick(item.id) }
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.item_padding_vertical)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {



            Row(verticalAlignment = Alignment.CenterVertically) {
                GlideImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    imageModel = audio.imgUri.ifEmpty { null },
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(20.dp))
                Column(modifier = Modifier.fillMaxWidth(fraction = 0.63f)) {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = audio.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.subtitle1.copy(color = itemColor)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = audio.artist,
                        style = MaterialTheme.typography.caption.copy(color = itemColor)
                    )
                }
                Spacer(Modifier.height(4.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.wrapContentWidth(),
                    text = audio.duration.toFormattedDuration().toString(),
                    color = if (nowPlaying) PrimaryColor else itemColor,
                    style = MaterialTheme.typography.caption
                )
                IconButton(
                    onClick = { moreIconClick(item) },
                    modifier = Modifier.wrapContentWidth(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = itemColor
                    )
                }
            }
        }

    }

}


@Composable
internal fun EmptyPlaylistViewRow(onAddAudio: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column() {
            Text(
                text = stringResource(id = R.string.empty_playlist_message),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Spacer(Modifier.height(10.dp))
            RoundedCornerShapeOutlinedButton(
                name = stringResource(id = R.string.add_audio_in_playlist),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onAddAudio
            )
        }
    }
}

