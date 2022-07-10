package com.pyc.local.dto

import androidx.room.*

@Entity(
    tableName = "audio_playlist"
)
internal data class AudioPlaylist(
    @PrimaryKey val colorPlaylistId: Int,
    val audioUri: String,
    val colorListInfoId: Int?,
    val order: Int
)
