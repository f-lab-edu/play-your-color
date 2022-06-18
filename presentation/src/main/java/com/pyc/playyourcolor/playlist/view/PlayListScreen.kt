package com.pyc.playyourcolor.playlist.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.pyc.playyourcolor.R

enum class PlayListScreen (
    val icon: ImageVector,
    val id: Int,
) {
    Primary(
        icon = Icons.Filled.LibraryMusic,
        id = R.string.primary_playlist,
    ),
    Color(
        icon = Icons.Filled.PlaylistPlay,
        id = R.string.color_playlist,
    ),
    Settings(
        icon = Icons.Filled.Settings,
        id = R.string.settings
    );

    companion object {
        fun fromRoute(route: String?): PlayListScreen =
            when (route?.substringBefore("/")) {
                Primary.name -> Primary
                Color.name -> Color
                Settings.name -> Settings
                null -> Primary
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}