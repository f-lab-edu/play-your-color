package com.pyc.local.dto

import androidx.room.*

@Entity(
    tableName = "color_playlist"
)
internal data class ColorPlaylist(
    @PrimaryKey val colorPlaylistId: Int,
    val audioUri: String,
    val colorListInfoId: Int,
    val order: Int
)