package com.pyc.playyourcolor.audiofilepicker.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pyc.playyourcolor.audiofilepicker.model.AudioFile
import com.pyc.playyourcolor.audiofilepicker.model.AudioFilePickerScreen
import com.pyc.playyourcolor.audiofilepicker.model.AudioFilePickerUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AudioFilePickerViewModel @AssistedInject constructor(
    @Assisted playlistId: Int,
) : ViewModel() {

    private var _uiState = mutableStateOf(
        AudioFilePickerUiState(
            AudioFilePickerScreen.LIST,
            "",
            listOf(
                AudioFile(1, "", "붉은노을", "빅뱅","","m4a", 350),
                AudioFile(2, "", "붉은노을", "빅뱅","","m4a", 350)
            ),
            listOf(
                AudioFile(1, "", "붉은노을", "빅뱅","","m4a", 350),
                AudioFile(2, "", "붉은노을", "빅뱅","","m4a", 350)
            )
        )
    )
    val uiState : State<AudioFilePickerUiState> get() = _uiState

    fun changeScreen() {
        if (_uiState.value.audioFilePickerScreen == AudioFilePickerScreen.SEARCH) {
            _uiState.value = _uiState.value.copy(audioFilePickerScreen = AudioFilePickerScreen.LIST)
        } else {
            _uiState.value = _uiState.value.copy(audioFilePickerScreen = AudioFilePickerScreen.SEARCH)
        }
    }

    fun deleteKeyword() {
        _uiState.value = _uiState.value.copy(searchKeyword = "")
    }

    fun inputKeyWord(keyWord: String) {
        _uiState.value = _uiState.value.copy(searchKeyword = keyWord)
    }

    fun selectAllAudioList() {
        with(_uiState.value) {
            if (audioFilePickerScreen == AudioFilePickerScreen.LIST) {
                if (audioList.count { it.selected } == audioList.size) {
                    _uiState.value = copy(
                        audioList = audioList.map { it.copy(selected = false) }
                    )
                } else {
                    _uiState.value = copy(
                        audioList = audioList.map { it.copy(selected = true) }
                    )
                }
            } else {
                if (searchList.count { it.selected } == searchList.size) {
                    _uiState.value = copy(
                        searchList = searchList.map { it.copy(selected = false) }
                    )
                } else {
                    _uiState.value = copy(
                        searchList = searchList.map { it.copy(selected = true) }
                    )
                }
            }
        }
    }

    fun addAudioToPlaylist() {
        // todo: playlist 에 선택된 오디오 추가
    }

    fun selectAudioFile(index: Int) {
        with(_uiState.value) {
            if (audioFilePickerScreen == AudioFilePickerScreen.LIST) {
                _uiState.value = copy(
                    audioList = audioList.mapIndexed { idx, audioFile ->
                        if (index == idx) {
                            audioFile.copy(selected = !audioFile.selected)
                        } else {
                            audioFile
                        }
                    }
                )
            } else {
                _uiState.value = copy(
                    searchList = searchList.mapIndexed { idx, audioFile ->
                        if (index == idx) {
                            audioFile.copy(selected = !audioFile.selected)
                        } else {
                            audioFile
                        }
                    }
                )
            }
        }
    }

    @AssistedFactory
    interface IdAssistedFactory {
        fun create(id: Int): AudioFilePickerViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: IdAssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}