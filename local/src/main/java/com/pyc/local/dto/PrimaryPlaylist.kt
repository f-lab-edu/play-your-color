package com.pyc.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "primary_playlist", indices = [Index(value = ["order"], unique = true)])
internal data class PrimaryPlaylist(
    @PrimaryKey val primaryPlayListId: Int,
    val audioUri: String,
    val order: Int
)