package com.pyc.presentation.audiofilepicker.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.pyc.presentation.audiofilepicker.view.ui.component.AudioFilePicker
import com.pyc.presentation.audiofilepicker.viewmodel.AudioFilePickerViewModel
import com.pyc.presentation.playlist.view.ui.theme.PlayYourColorTheme
import dagger.hilt.android.AndroidEntryPoint
import pyc.domain.common.PRIMARY_PLAYLIST_ID
import javax.inject.Inject

@AndroidEntryPoint
class AudioFilePickerActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: AudioFilePickerViewModel.IdAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val playlistId : Int = (intent.extras?.get(PLAYLIST_KEY) as? Int) ?: PRIMARY_PLAYLIST_ID
            PlayYourColorTheme {
                Surface {
                    AudioFilePicker(playlistId, viewModelFactory)
                }
            }
        }
    }

    companion object {
        const val PLAYLIST_KEY = "playlist_id"
    }
}
