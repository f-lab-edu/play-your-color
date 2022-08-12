package com.pyc.presentation.playlist.view.ui.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pyc.presentation.extensions.toFormattedDuration
import com.pyc.presentation.model.AudioPlaylistItemModel
import com.pyc.presentation.model.PlayListItemAudioState
import com.pyc.presentation.playlist.view.ui.theme.PrimaryColor
import com.skydoves.landscapist.glide.GlideImage
import com.pyc.presentation.R


@Composable
internal fun PlaylistRow(
    playlist: List<AudioPlaylistItemModel>,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
) {

    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(playlist) { position, item ->
            PlaylistItemRow(
                position = position,
                item = item,
                itemClick = itemClick,
                itemLongClick = itemLongClick,
                moreIconClick = moreIconClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PlaylistItemRow(
    position: Int,
    item: AudioPlaylistItemModel,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit
) {
    val audio = item.audio
    Box(Modifier.wrapContentSize()) {

        val itemColor = when (item.itemState) {
            PlayListItemAudioState.CanNotPlayAudio -> Color.Gray
            else -> Color.White
        }

        DefaultPlaylistItemRow(
            position = position,
            titleAndArtistSlot = {
                AudioTitleAndArtist(
                    title = audio.title,
                    artist = audio.artist,
                    defaultColor = when (item.itemState) {
                        PlayListItemAudioState.Default -> Color.White
                        PlayListItemAudioState.NowPlaying -> PrimaryColor
                        PlayListItemAudioState.CanNotPlayAudio -> Color.Gray
                    }
                )
            },
            item = item,
            itemColor = itemColor,
            itemClick = itemClick,
            itemLongClick = itemLongClick,
            moreIconClick = moreIconClick
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DefaultPlaylistItemRow(
    position: Int,
    titleAndArtistSlot: @Composable() () -> Unit,
    itemColor: Color,
    item: AudioPlaylistItemModel,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit
) {
    val audio = item.audio

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                Color(
                    1f,
                    1f,
                    1f,
                    if (item.itemState == PlayListItemAudioState.CanNotPlayAudio) 0.3f else 0f,
                )
            )
            .combinedClickable(
                onClick = { itemClick(item.id, item) },
                onLongClick = { itemLongClick(position) }
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

            titleAndArtistSlot()

            Spacer(Modifier.height(4.dp))
        }
        Text(
            modifier = Modifier.wrapContentWidth(Alignment.End),
            text = audio.duration.toFormattedDuration(),
            color = itemColor,
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


@Composable
internal fun AudioTitleAndArtist(
    modifier: Modifier = Modifier,
    title: String,
    artist: String,
    defaultColor: Color,
    searchWord: String = ""
) {

    fun annotatedString(string: String): AnnotatedString {
        return buildAnnotatedString {
            val startIndex = string.indexOf(searchWord)
            val endIndex = startIndex + searchWord.length
            withStyle(style = SpanStyle(defaultColor)) {
                append(string)
            }
            addStyle(
                style = SpanStyle(PrimaryColor),
                start = startIndex,
                end = endIndex
            )
        }
    }

    Column(modifier = Modifier.fillMaxWidth(fraction = 0.63f)) {

        Text(
            modifier = Modifier.wrapContentSize(),
            text = annotatedString(title),
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1.copy(defaultColor)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = annotatedString(artist),
            style = MaterialTheme.typography.caption.copy(defaultColor)
        )
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

