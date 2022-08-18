package com.pyc.presentation.model

internal class AudioPlaylistItemModel(
    val id: Int,
    val audio: AudioModel,
    val initItemState: PlayListItemAudioState = PlayListItemAudioState.Default
)
