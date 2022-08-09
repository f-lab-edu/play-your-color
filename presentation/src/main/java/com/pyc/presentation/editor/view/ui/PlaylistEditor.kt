package com.pyc.presentation.editor.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pyc.presentation.R
import com.pyc.presentation.editor.view.ui.components.*
import com.pyc.presentation.editor.viewmodel.PlaylistEditorViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaylistEditor(
    playlistId: Int,
    factory: PlaylistEditorViewModel.IdAssistedFactory,
    viewModel: PlaylistEditorViewModel = viewModel(factory = PlaylistEditorViewModel.provideFactory(factory, playlistId))
) {

    val audioEditModelList = viewModel.audioEditModelList

    val bottomSheetScaffoldState = viewModel.bottomSheetScaffoldState

    val selectedCountState = viewModel.selectedCountState

    val colorInfoState = viewModel.colorInfoState

    var deleteDialogState by rememberSaveable { mutableStateOf(false) }

    val dragDropListState = rememberDragDropListState(onMove = { from, to, callback ->
        // onMove 말고 drag end 일떄 업데이트?
        viewModel.audioMove(from, to, callback)
    })

    if (deleteDialogState) {
        EditorDeleteDialog(
            onDelete = {
                viewModel.deleteSelectedAudio()
                deleteDialogState = false },
            onCancel = { deleteDialogState = false }
        )
    }

    PlaylistEditorScreen(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        selectedCountState = selectedCountState,
        dragDropListState = dragDropListState,
        items = audioEditModelList.value,
        title = colorInfoState.value?.name ?: stringResource(id = R.string.primary_playlist_editor),
        topMenu = {
            Box(
                Modifier
                .fillMaxWidth()
            ){
                Row(modifier = Modifier
                    .align(Alignment.CenterStart)
                ) {
                    EditorPlusAudioButton {
                        // todo: 곡 추가화면 띄우기
                    }
                }
                EditorAllChoiceButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    maxSelectedCount = audioEditModelList.value.size,
                    selectedCount = selectedCountState.value
                ) {
                    viewModel.selectAllAudio()
                }
            }
        },
        bottomMenu = {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                EditorBottomIconButton(
                    imageVector = Icons.Filled.Add,
                    text = stringResource(id = R.string.add)
                ) {
                    // todo: 담기 화면
                }

                EditorBottomIconButton(
                    imageVector = Icons.Filled.Delete,
                    text = stringResource(id = R.string.delete)
                ) {
                    deleteDialogState = true
                }

                EditorBottomIconButton(
                    imageVector = Icons.Filled.Cancel,
                    text = stringResource(id = R.string.choice_cancel)
                ) {
                    viewModel.deselectAllAudio()
                }
            }
        },
        onCompleted = { _ ->
            // todo: 뒤로가기
        },
        onItemClicked = { idx ->
            viewModel.selectAudio(idx)
        },
    )
}