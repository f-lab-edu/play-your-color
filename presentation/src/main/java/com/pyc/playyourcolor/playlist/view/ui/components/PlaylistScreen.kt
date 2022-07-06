package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
    nowPlaying: Boolean = false,
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
            .padding(all = dimensionResource(id = R.dimen.padding_row_item))
    ) {
        topBarSlot()
        val (searchMode, setSearchMode) = remember {
            mutableStateOf(false)
        }
        if (!searchMode) {
            PlaylistFunctionBarRow(
                listCount = playlist.size,
                playlistId = playlistId,
                onSearchMode = { /*TODO*/ },
                onAddAudio = onAddAudio,
                onEditPlaylist = onEditPlaylist
            )
        } else {
            //TODO 수정예정
            val searchWord = ""
            SearchBarRow(
                searchWord,
                onSearchCanceled = { setSearchMode(false) }, onSearchAudioInList
            )
        }

        //playlist영역
        if (playlist.isNotEmpty()) {
            PlaylistRow(playlist, nowPlaying, itemClick, itemLongClick, moreIconClick)
        } else {
            EmptyPlaylistViewRow { onAddAudio(playlistId) }
        }
    }
}