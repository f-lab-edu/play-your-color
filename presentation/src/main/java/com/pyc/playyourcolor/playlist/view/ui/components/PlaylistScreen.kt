package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.model.AudioPlaylistItemModel

/**
 * 매개변수 ViewModel 로 변경 예정
 *
 */
@Composable
internal fun PlaylistScreenComponent(
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    nowPlayingAudioId: Int?,
    itemClick: (Int, Boolean) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
    topBarSlot: @Composable() () -> Unit,
    onAddAudio: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onSearchAudioInList: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        topBarSlot()
        val (searchMode, setSearchMode) = remember {
            mutableStateOf(false)
        }
        if (!searchMode) {
            PlaylistFunctionBarRow(
                Modifier.padding(horizontal = dimensionResource(id = R.dimen.item_padding_horizontal), vertical = dimensionResource(
                    id = R.dimen.item_padding_vertical
                )).fillMaxWidth(),
                listCount = playlist.size,
                playlistId = playlistId,
                onSearchMode = { setSearchMode(true) },
                onAddAudio = onAddAudio,
                onEditPlaylist = onEditPlaylist
            )
        } else {
            //TODO 수정예정
            val searchWord = ""
            SearchBarRow(
                Modifier.padding(horizontal = dimensionResource(id = R.dimen.item_padding_horizontal), vertical = dimensionResource(
                    id = R.dimen.item_padding_vertical
                )),
                searchWord,
                onSearchCanceled = { setSearchMode(false) }, onSearchAudioInList
            )
        }

        //playlist영역
        if (playlist.isNotEmpty()) {
            PlaylistRow(playlist, nowPlayingAudioId, itemClick, itemLongClick, moreIconClick)
        } else {
            EmptyPlaylistViewRow { onAddAudio(playlistId) }
        }
    }
}