package com.pyc.playyourcolor.audiofilepicker.model

data class AudioFilePickerUiState(
    val audioFilePickerScreen: AudioFilePickerScreen,
    val searchKeyword: String,
    val searchList: List<AudioFile>,
    val audioList: List<AudioFile>
)