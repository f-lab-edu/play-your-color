package com.pyc.playyourcolor.model

import pyc.domain.model.ColorInfo

internal data class AudioModel(
        val uri: String,
        var title: String = "",
        var artist: String = "",
        val duration: Long = 0,
        var imgUri: String = "",
        val mimeType: String = "",
        val colorInfoList: List<ColorInfo> = listOf(),
        var playPossible: Boolean = true,
        var nowPlaying: Boolean = false
)