package com.pyc.playyourcolor.audiofilepicker.model

data class AudioFile(
    val id: Long,
    val thumbnail: String,
    val title: String,
    val artist: String,
    val uri: String,
    val mimeType: String,
    val duration: Int,
    val selected: Boolean = false,
)
