package com.pyc.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "color_list_info", indices = [Index(value = ["color_hex"], unique = true),
    Index(value = ["order"], unique = true)])
internal data class ColorlistInfo(
    @PrimaryKey val colorListInfoId: Int,
    @ColumnInfo(name = "color_hex")val colorHex: String,
    val colorName: String,
    val order: Int
)