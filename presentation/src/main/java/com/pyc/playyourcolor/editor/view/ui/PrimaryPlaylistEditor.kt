package com.pyc.playyourcolor.editor.view.ui

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
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.common.collapse
import com.pyc.playyourcolor.common.expand
import com.pyc.playyourcolor.editor.view.ui.components.*
import com.pyc.playyourcolor.editor.viewmodel.PlaylistEditorViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PrimaryPlaylistEditor(
    playlistId: Int,
    factory: PlaylistEditorViewModel.IdAssistedFactory,
    viewModel: PlaylistEditorViewModel = viewModel(factory = PlaylistEditorViewModel.provideFactory(factory, playlistId))
) {

    val audioEditModelList = viewModel.audioEditModelList

    val bottomSheetScaffoldState = viewModel.bottomSheetScaffoldState

    val selectedCountState = viewModel.selectedCountState

    var deleteDialogState by rememberSaveable { mutableStateOf(false) }

    if (selectedCountState.value > 0) {
        LaunchedEffect(key1 = bottomSheetScaffoldState) {
            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                bottomSheetScaffoldState.expand(this)
            }
        }
    } else {
        LaunchedEffect(key1 = bottomSheetScaffoldState) {
            if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                bottomSheetScaffoldState.collapse(this)
            }
        }
    }

    if (deleteDialogState) {
        if (selectedCountState.value > 0) {
            EditorDeleteDialog(
                onDelete = {
                    viewModel.deleteSelectedAudio()
                    deleteDialogState = false },
                onCancel = { deleteDialogState = false }
            )
        } else {
            EditorEmptyChoiceDialog { deleteDialogState = false}
        }
    }

    PlaylistEditorScreen(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        items = audioEditModelList,
        title = stringResource(id = R.string.primary_playlist_editor),
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
                    maxSelectedCount = audioEditModelList.size,
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
        onMove = { from, to ->
            // onMove 말고 drag end 일떄 업데이트?
            viewModel.audioMove(from, to)
        }
    )
}