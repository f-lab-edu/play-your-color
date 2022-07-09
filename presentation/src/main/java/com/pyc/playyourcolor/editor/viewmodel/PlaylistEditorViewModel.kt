package com.pyc.playyourcolor.editor.viewmodel

import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pyc.playyourcolor.common.move
import com.pyc.playyourcolor.editor.model.AudioEditModel
import com.pyc.playyourcolor.editor.model.toAudioEditModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pyc.domain.usecase.DeleteAudioUseCase
import pyc.domain.usecase.GetPlaylistUseCase
import pyc.domain.usecase.UpdatePlaylistAudioOrderUseCase

class PlaylistEditorViewModel @AssistedInject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val updatePlaylistAudioOrderUseCase: UpdatePlaylistAudioOrderUseCase,
    private val deleteAudioUseCase: DeleteAudioUseCase,
    @Assisted private val playlistId: Int,
) : ViewModel() {

    private var _audioEditModelList = mutableStateListOf<AudioEditModel>()
    val audioEditModelList : List<AudioEditModel> get() = _audioEditModelList

    private var _selectedCountState = mutableStateOf(0)
    val selectedCountState : State<Int> get() = _selectedCountState

    @OptIn(ExperimentalMaterialApi::class)
    val bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getAudioEditModelList(playlistId)
    }

    private fun getAudioEditModelList(id: Int) {
        val disposable = getPlaylistUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { _audioEditModelList.addAll(it.map { item -> toAudioEditModel(item) }) },
                { throwable -> },
                {})
        compositeDisposable.add(disposable)
    }

    fun audioMove(from: Int, to: Int) {
        updatePlaylistAudioOrderUseCase(playlistId, _audioEditModelList.map { it.id }.toMutableList(), from, to)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _audioEditModelList.move(from, to)
            }
            .subscribe()
    }

    fun selectAudio(idx: Int) {
        if (_audioEditModelList[idx].checked) {
            _audioEditModelList[idx].checked = false
            _selectedCountState.value--
        } else {
            _audioEditModelList[idx].checked = true
            _selectedCountState.value++
        }
    }

    fun deselectAllAudio() {
        _audioEditModelList.forEach {
            if (it.checked) {
                it.checked = false
                _selectedCountState.value--
            }
        }
    }

    fun deleteSelectedAudio() {
        deleteAudioUseCase(
            playlistId,
            _audioEditModelList.map { it.id },
            _audioEditModelList.filter { it.checked }.map { item -> item.id })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _audioEditModelList.removeAll { it.checked }
            }
            .subscribe()
    }

    fun selectAllAudio() {
        if (_selectedCountState.value < _audioEditModelList.size) {
            _audioEditModelList.forEach {
                if (!it.checked) {
                    it.checked = true
                    _selectedCountState.value++
                }
            }
        } else {
            _audioEditModelList.forEach {
                it.checked = false
                _selectedCountState.value--
            }
        }
    }

    @AssistedFactory
    interface IdAssistedFactory {
        fun create(id: Int): PlaylistEditorViewModel
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
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