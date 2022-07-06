package com.pyc.playyourcolor.playlist.view.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.playlist.view.ui.theme.GreyColor
import com.pyc.playyourcolor.playlist.view.ui.theme.Purple200


/**
 * TODO 리스트 곡수 변경에 따른 카운팅 UI 변경, 검색, 추가, 편집 버튼 클릭 리스너 추가
 *
 */
@Preview
@Composable
internal fun PlaylistFunctionBarRow(
    listCount: Int,
    playlistId: Int,
    onSearchMode: () -> Unit,
    onAddAudio: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit

) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.wrapContentWidth(Alignment.Start),
            text = stringResource(id = R.string.audio_count, listCount),
            style = MaterialTheme.typography.caption.copy(color = Purple200)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onSearchMode,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
                    .padding(10.dp)
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            RoundedCornerShapeOutlinedButton(
                stringResource(id = R.string.add),
                modifier = Modifier.wrapContentSize(),
                onClick = { onAddAudio(playlistId) })
            Spacer(Modifier.width(5.dp))
            RoundedCornerShapeOutlinedButton(
                stringResource(id = R.string.edit),
                modifier = Modifier.wrapContentSize(),
                onClick = { onEditPlaylist(playlistId) })


        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
internal fun SearchBarRow(
    searchWord: String,
    onSearchCanceled: () -> Unit,
    onSearchAudioInList: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.fillMaxWidth(0.1f),
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.White
        )
        val word by remember { mutableStateOf(TextFieldValue("")) }
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = searchWord,
            onValueChange = onSearchAudioInList,
            label = {
                Text(
                    color = GreyColor,
                    text = stringResource(id = R.string.search_playlist)
                )
            },
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            })
        )
        RoundedCornerShapeOutlinedButton(
            stringResource(id = R.string.cancel),
            onClick = onSearchCanceled
        )

    }

}