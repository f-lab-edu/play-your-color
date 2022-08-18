package com.pyc.presentation.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed interface PlaylistScreen
sealed interface ColorPlaylistScreen

class Search(val searchState: SearchState = EmptyWord) : PlaylistScreen
object Playlist : PlaylistScreen, ColorPlaylistScreen
object ColorList : ColorPlaylistScreen
