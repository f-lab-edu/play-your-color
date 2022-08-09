package com.pyc.presentation.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed interface PlaylistScreen
sealed interface ColorPlaylistScreen

class Search(initStatus: SearchState = EmptyWord) : PlaylistScreen, ColorPlaylistScreen {
    var status: SearchState by mutableStateOf(initStatus)
}
object Playlist : PlaylistScreen, ColorPlaylistScreen
object ColorList : ColorPlaylistScreen
