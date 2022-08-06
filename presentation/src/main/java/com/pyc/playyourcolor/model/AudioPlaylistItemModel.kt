package com.pyc.playyourcolor.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

internal class AudioPlaylistItemModel(
    val id: Int,
    val audio: AudioModel,
    initItemState: PlayListItemAudioState = PlayListItemAudioState.Default
) {
    var itemState: PlayListItemAudioState by mutableStateOf(initItemState)
}
