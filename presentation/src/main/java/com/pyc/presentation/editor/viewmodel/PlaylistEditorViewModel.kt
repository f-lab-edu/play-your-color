package com.pyc.presentation.editor.viewmodel

import androidx.compose.material.*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pyc.presentation.common.move
import com.pyc.presentation.editor.model.AudioEditModel
import com.pyc.presentation.editor.model.toAudioEditModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pyc.domain.common.PRIMARY_PLAYLIST_ID
import pyc.domain.model.ColorInfo
import pyc.domain.usecase.*

class PlaylistEditorViewModel @AssistedInject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val updatePlaylistAudioOrderUseCase: UpdatePlaylistAudioOrderUseCase,
    private val deleteAudioUseCase: DeleteAudioUseCase,
    private val updateColorInfoUseCase: UpdateColorInfoUseCase,
    private val getColorInfoUseCase: GetColorInfoUseCase,
    @Assisted private val playlistId: Int,
) : ViewModel() {

    private var _audioEditModelList = mutableStateOf<List<AudioEditModel>>(emptyList())
    val audioEditModelList: State<List<AudioEditModel>> get() = _audioEditModelList

    private var _selectedCountState = mutableStateOf(0)
    val selectedCountState: State<Int> get() = _selectedCountState

    @OptIn(ExperimentalMaterialApi::class)
    val bottomSheetScaffoldState = BottomSheetScaffoldState(
        drawerState = DrawerState(DrawerValue.Closed),
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed),
        SnackbarHostState()
    )

    private var _colorInfoState = mutableStateOf<ColorInfo?>(null)
    val colorInfoState: State<ColorInfo?> get() = _colorInfoState

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getAudioEditModelList(playlistId)
        if (playlistId != PRIMARY_PLAYLIST_ID) {
            getColorInfo(playlistId)
        }
    }

    private fun getAudioEditModelList(id: Int) {
        val disposable = getPlaylistUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { _audioEditModelList.value = it.map { item -> toAudioEditModel(item) }},
                { throwable -> },
                {})
        compositeDisposable.add(disposable)
    }

    private fun getColorInfo(id: Int) {
        val disposable = getColorInfoUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _colorInfoState.value = it },
                {},
                {}
            )
        compositeDisposable.add(disposable)
    }

    fun audioMove(from: Int, to: Int, callback: () -> Unit) {
        updatePlaylistAudioOrderUseCase(playlistId, _audioEditModelList.value.map { it.id }.toMutableList(), from, to)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _audioEditModelList.value = _audioEditModelList.value.move(from, to)
                callback()
            }
            .subscribe()
    }

    fun selectAudio(idx: Int) {
        if (_audioEditModelList.value[idx].checked) {
            _audioEditModelList.value[idx].checked = false
            _selectedCountState.value--
        } else {
            _audioEditModelList.value[idx].checked = true
            _selectedCountState.value++
        }
    }

    fun deselectAllAudio() {
        _audioEditModelList.value.forEach {
            if (it.checked) {
                it.checked = false
                _selectedCountState.value--
            }
        }
    }

    fun deleteSelectedAudio() {
        deleteAudioUseCase(
            playlistId,
            _audioEditModelList.value.map { it.id },
            _audioEditModelList.value.filter { it.checked }.map { item -> item.id })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _audioEditModelList.value = _audioEditModelList.value.filterNot{ it.checked }
                _selectedCountState.value = 0
            }
            .subscribe()
    }

    fun selectAllAudio() {
        if (_selectedCountState.value < _audioEditModelList.value.size) {
            _audioEditModelList.value.forEach {
                if (!it.checked) {
                    it.checked = true
                    _selectedCountState.value++
                }
            }
        } else {
            _audioEditModelList.value.forEach {
                it.checked = false
                _selectedCountState.value--
            }
        }
    }

    sealed class Event {
        class UpdateOrderFail() : Event()
        class UpdateOrderComplete(val from: Int, val to: Int) : Event()
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