package com.pyc.presentation.playlist.view.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pyc.presentation.R
import com.pyc.presentation.model.*
import com.pyc.presentation.model.AudioModel
import com.pyc.presentation.model.AudioPlaylistItemModel

/**
 * 매개변수 ViewModel 로 변경 예정
 *
 */
@Composable
internal fun PlaylistScreenComponent(
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
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
        val (screenMode, setScreenMode) = remember {
            mutableStateOf<PlaylistScreen>(Playlist)
        }
        //TODO 수정예정
        var searchWord by remember { mutableStateOf("") }
        var searchList by remember {
            mutableStateOf<List<AudioPlaylistItemModel>>(listOf())
        }
        when (screenMode) {
            is Playlist -> {
                PlaylistFunctionBarRow(
                    Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                            vertical = dimensionResource(
                                id = R.dimen.item_padding_vertical
                            )
                        )
                        .fillMaxWidth(),
                    listCount = playlist.size,
                    playlistId = playlistId,
                    onSearchModeOn = { setScreenMode(Search()) },
                    onAddAudio = onAddAudio,
                    onEditPlaylist = onEditPlaylist
                )
                if (playlist.isNotEmpty()) {
                    PlaylistRow(playlist, itemClick, itemLongClick, moreIconClick)
                } else {
                    EmptyPlaylistViewRow { onAddAudio(playlistId) }
                }
            }
            is Search -> {
                SearchBarRow(
                    Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                        vertical = dimensionResource(
                            id = R.dimen.item_padding_vertical
                        )
                    ),
                    searchWord,
                    onSearchCanceled = { setScreenMode(Playlist) }, onSearchAudioInList = {
                        searchList = listOf(
                            AudioPlaylistItemModel(
                                id = 1,
                                audio = AudioModel(
                                    "test",
                                    "TT",
                                    "트와이스",
                                    300000,
                                    "https://i.imgur.com/93QXZlj.png",
                                    "mp3"
                                ),
                                PlayListItemAudioState.CanNotPlayAudio
                            )
                        )
                        searchWord = it
                        screenMode.status = if (searchWord.isEmpty()) {
                            EmptyWord
                        } else if (searchList.isNotEmpty()) {
                            ExistResultList
                        } else {
                            NoResultList
                        }
                    }
                )

                when (screenMode.status) {
                    is EmptyWord -> {
                        PlaylistRow(playlist, itemClick, itemLongClick, moreIconClick)
                    }
                    is ExistResultList -> {
                        SearchResultPlaylistRow(
                            searchWord,
                            searchList,
                            itemClick,
                            itemLongClick,
                            moreIconClick
                        )
                    }
                    is NoResultList -> {
                        NoSearchResultViewRow()
                    }
                }

            }
        }
    }


}


