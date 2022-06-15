package com.pyc.local.dto

import androidx.room.*

@Entity(
    tableName = "color_playlist"
)
internal data class ColorPlaylist(
    @PrimaryKey val colorPlayListId: Int,
    val audioUri: String,
    val order: Int
)