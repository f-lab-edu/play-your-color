package com.pyc.playyourcolor.model

sealed class PlayListItemAudioState {

    object NowPlaying : PlayListItemAudioState()
    object CanNotPlayAudio : PlayListItemAudioState()
    object Default : PlayListItemAudioState()

}
