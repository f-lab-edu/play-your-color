package com.pyc.presentation.playlist.view.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.pyc.presentation.R
import com.pyc.presentation.model.AudioPlaylistItemModel
import com.pyc.presentation.model.PlayListItemAudioState

@Composable
internal fun SearchResultPlaylistRow(
    word: String,
    searchResultPlaylist: List<AudioPlaylistItemModel>,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
) {

    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(searchResultPlaylist) { position, item ->
            SearchResultPlaylistItemRow(
                position, word, item, itemClick, itemLongClick, moreIconClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchResultPlaylistItemRow(
    position: Int,
    searchWord: String,
    item: AudioPlaylistItemModel,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit
) {
    val audio = item.audio
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
                defaultColor = itemColor,
                searchWord = searchWord
            )
        },
        item = item,
        itemColor = itemColor,
        itemClick = itemClick,
        itemLongClick = itemLongClick,
        moreIconClick = moreIconClick

    )
}


@Composable
internal fun NoSearchResultViewRow() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.no_search_result_message),
            color = Color.White
        )
    }
}