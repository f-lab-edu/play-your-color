package com.pyc.playyourcolor.editor.viewmodel

import androidx.lifecycle.ViewModel
import com.pyc.playyourcolor.editor.model.AudioEditModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistEditorViewModel @Inject constructor(

) : ViewModel() {

    private var _liveAudioEditModelList = mutableListOf<AudioEditModel>()
    val liveAudioEditModelList : List<AudioEditModel> get() = _liveAudioEditModelList

    fun getAudioEditModelList(id: Int) {

    }
}