package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.extensions.toFormattedDuration
import com.pyc.playyourcolor.model.AudioModel
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.playlist.view.ui.theme.PrimaryColor


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
        if (!item.audio.playPossible) {
            //TODO 재생 불가할 때 PlayImpossibleBackgroundColor 씌우기
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = { itemClick(item.id, item.audio.playPossible) },
                    onLongClick = { itemLongClick(item.id) }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {


            AudioImageContainer(modifier = Modifier.size(40.dp)) {
                AudioImage(imgUri = audio.imgUri)
            }
            Spacer(Modifier.width(20.dp))
            Column(modifier = Modifier.fillMaxWidth(fraction = 0.73f)) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = audio.title,
                    style = MaterialTheme.typography.subtitle1.copy(color = Color.White)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = audio.artist,
                    style = MaterialTheme.typography.caption.copy(color = Color.White)
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.wrapContentWidth(Alignment.End),
                text = audio.duration.toFormattedDuration().toString(),
                color = if (nowPlaying) PrimaryColor else Color.White,
                style = MaterialTheme.typography.caption
            )
            IconButton(
                onClick = { moreIconClick(item) },
                modifier = Modifier.wrapContentWidth(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }

}

@Preview
@Composable
internal fun PreviewPlaylistItemRow() {
    PlaylistItemRow(
        item =
        AudioPlaylistItemModel(
            id = 1,
            audio = AudioModel(
                "test",
                "TT",
                "트와이스",
                300000,
                "https://imgur.com/93QXZlj",
                "mp3"
            )
        ),
        itemClick = { id, playPossible -> },
        itemLongClick = { id -> },
        moreIconClick = { item -> }
    )
}


@Composable
private fun AudioImage(imgUri: String) {
    Box {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imgUri)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Composable
private fun AudioImageContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), RoundedCornerShape(2.dp)) {
        content()
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
                name = stringResource(id = R.string.add_audio),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onAddAudio
            )
        }
    }
}

