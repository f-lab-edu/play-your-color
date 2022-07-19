package com.pyc.playyourcolor.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pyc.playyourcolor.editor.view.ui.PlaylistEditor
import com.pyc.playyourcolor.editor.viewmodel.PlaylistEditorViewModel
import com.pyc.playyourcolor.playlist.view.ui.theme.PlayYourColorTheme
import dagger.hilt.android.AndroidEntryPoint
import pyc.domain.common.PRIMARY_PLAYLIST_ID
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistEditorActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: PlaylistEditorViewModel.IdAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayYourColorTheme {
                val playlistId : Int = (intent.extras?.get(PLAYLIST_EDITOR_ACTIVITY_KEY) as? Int) ?: PRIMARY_PLAYLIST_ID
                PlaylistEditor(playlistId, viewModelFactory)
            }
        }
    }

    companion object {
        const val PLAYLIST_EDITOR_ACTIVITY_KEY = "playlistId"
    }
}
