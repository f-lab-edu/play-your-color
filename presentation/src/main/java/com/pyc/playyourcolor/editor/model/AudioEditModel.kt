package com.pyc.playyourcolor.editor.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import pyc.domain.model.PlaylistItem

class AudioEditModel(
    val id: Int,
    val uri: String,
    val title: String = "",
    val artist: String = "",
    val imgUri: String = "",
    val playPossible: Boolean = true,
    initialChecked: Boolean = false
) {
    var checked: Boolean by mutableStateOf(initialChecked)
}

fun toAudioEditModel(playlistItem: PlaylistItem): AudioEditModel =
    AudioEditModel(
        id = playlistItem.id,
        uri = playlistItem.audio.uri,
        title = playlistItem.audio.title,
        artist = playlistItem.audio.artist,
        imgUri = playlistItem.audio.imgUri,
        playPossible = playlistItem.audio.playPossible
    )
