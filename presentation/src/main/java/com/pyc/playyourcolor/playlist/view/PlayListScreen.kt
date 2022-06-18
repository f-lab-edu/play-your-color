package com.pyc.playyourcolor.playlist.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class PlayListScreen (val icon: ImageVector) {
    Primary(
        icon = Icons.Filled.LibraryMusic,
    ),
    Color(
        icon = Icons.Filled.PlaylistPlay,
    ),
    Settings(
        icon = Icons.Filled.Settings
    )
}