package com.pyc.presentation.audiofilepicker.view.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pyc.presentation.R
import com.pyc.presentation.audiofilepicker.viewmodel.AudioFilePickerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pyc.presentation.audiofilepicker.model.AudioFilePickerScreen
import com.pyc.presentation.editor.view.ui.components.EditorAllChoiceButton
import pyc.domain.common.PRIMARY_PLAYLIST_ID

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AudioFilePicker(
    playlistId: Int = PRIMARY_PLAYLIST_ID,
    factory: AudioFilePickerViewModel.IdAssistedFactory,
    viewModel: AudioFilePickerViewModel = viewModel(factory = AudioFilePickerViewModel.provideFactory(factory, playlistId))
) {

    val uiState = viewModel.uiState
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 15.dp, bottom = 15.dp)
        ) {
            ClickableText(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 20.sp
                        )
                    ) {
                        append(stringResource(id = R.string.add_audio))
                    }
                },
                onClick = {  }
            )
        }
        if (uiState.value.audioFilePickerScreen == AudioFilePickerScreen.LIST) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                EditorAllChoiceButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    maxSelectedCount = uiState.value.audioList.size,
                    selectedCount = uiState.value.audioList.count { it.selected },
                    onClick = {
                        viewModel.selectAllAudioList()
                    }
                )
                IconButton(
                    onClick = { viewModel.changeScreen() },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(id = R.string.search))
                }
            }
            AudioFilePickerList(
                audioList = uiState.value.audioList,
                itemClicked = { index, _ -> viewModel.selectAudioFile(index) }
            )
        } else {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                value = uiState.value.searchKeyword,
                onValueChange = { viewModel.inputKeyWord(it)},
                leadingIcon = { AudioFilePickerBackButton {
                    viewModel.changeScreen()
                } },
                trailingIcon = { AudioFilePickerDeleteButton {
                    viewModel.deleteKeyword()
                }},
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        // 검색
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )
            AudioFilePickerList(
                audioList = viewModel.uiState.value.searchList,
                itemClicked = { index, _ -> viewModel.selectAudioFile(index) }
            )
        }
    }
}

@Composable
fun AudioFilePickerBackButton(onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back))
    }
}

@Composable
fun AudioFilePickerDeleteButton(onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
    ) {
        Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(id = R.string.delete))
    }
}