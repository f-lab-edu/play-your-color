package com.pyc.playyourcolor.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pyc.playyourcolor.editor.view.ui.components.PrimaryPlaylistEditor
import com.pyc.playyourcolor.playlist.view.ui.theme.PlayYourColorTheme
import pyc.domain.common.PRIMARY_PLAYLIST_ID

class PlaylistEditorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayYourColorTheme {
                val colorInfoId : Int = intent.extras?.get(PLAYLIST_EDITOR_ACTIVITY_KEY) as Int
                if (colorInfoId == PRIMARY_PLAYLIST_ID) {
                    PrimaryPlaylistEditor()
                } else {

                }
            }
        }
    }

    companion object {
        const val PLAYLIST_EDITOR_ACTIVITY_KEY = "colorInfoId"
    }
}
