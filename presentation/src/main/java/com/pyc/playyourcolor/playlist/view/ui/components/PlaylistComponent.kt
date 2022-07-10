package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.extensions.toFormattedDuration
import com.pyc.playyourcolor.model.AudioModel
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.playlist.view.ui.theme.PrimaryColor
import com.skydoves.landscapist.glide.GlideImage


@Composable
internal fun PlaylistRow(
    playlist: List<AudioPlaylistItemModel>,
    nowPlaying: Boolean = false,
    itemClick: (Int, Boolean) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(playlist) { index, item ->
            PlaylistItemRow(nowPlaying, item, itemClick, itemLongClick, moreIconClick)
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
    Box(Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(
                        1f,
                        1f,
                        1f,
                        if (nowPlaying) 0.3f else if (!item.audio.playPossible) 0.6f else 0f
                    )
                )
                .combinedClickable(
                    onClick = { itemClick(item.id, item.audio.playPossible) },
                    onLongClick = { itemLongClick(item.id) }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val itemColor = if (item.audio.playPossible) Color.White else Color.Gray

            GlideImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                imageModel = audio.imgUri.ifEmpty { null },
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(20.dp))
            Column(modifier = Modifier.fillMaxWidth(fraction = 0.63f)) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = audio.title,
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
            Text(
                modifier = Modifier.wrapContentWidth(Alignment.End),
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

@Preview
@Composable
internal fun PreviewPlaylistItemRow() {
    PlaylistItemRow(
        nowPlaying = false,
        item =
        AudioPlaylistItemModel(
            id = 1,
            audio = AudioModel(
                "test",
                "TT",
                "트와이스",
                300000,
                "https://i.imgur.com/93QXZlj.png",
                "mp3"
            )
        ),
        itemClick = { id, playPossible -> },
        itemLongClick = { id -> },
        moreIconClick = { item -> }
    )
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
                name = stringResource(id = R.string.add_audio),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onAddAudio
            )
        }
    }
}

