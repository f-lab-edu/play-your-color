package com.pyc.playyourcolor.playlist.view.ui.colorplaylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.model.AudioModel
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.model.ColorModel
import com.pyc.playyourcolor.playlist.view.ui.components.ColorListRow
import com.pyc.playyourcolor.playlist.view.ui.components.PlaylistScreenComponent
import com.pyc.playyourcolor.playlist.view.ui.components.RoundedCornerShapeOutlinedButton

@Composable
internal fun ColorPlayListScreen(
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    nowPlaying: Boolean = false,
    itemClick: (Int, Boolean) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
    onAddAudio: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onSearchAudioInList: (String) -> Unit
) {
    Text(text = "컬러 플레이리스트 화면")

    PlaylistScreenComponent(
        topBarSlot = {
            ColorPlaylistHeaderRow(colorModel = ColorModel(1, "#ffee11", "오렌지"))
        },
        playlist = playlist,
        playlistId = playlistId,
        nowPlaying = nowPlaying,
        itemClick = itemClick,
        itemLongClick = itemLongClick,
        moreIconClick = moreIconClick,
        onAddAudio = onAddAudio,
        onEditPlaylist = onEditPlaylist,
        onSearchAudioInList = onSearchAudioInList


    )

}



@Composable
private fun PreviewColorPlaylistHeaderRow() {
    ColorPlaylistHeaderRow(colorModel = ColorModel(1, "#ffee11", "오렌지"))
}


@Composable
internal fun ColorPlaylistHeaderRow(colorModel: ColorModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.padding_row_item))
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = colorModel.name,
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )

        RoundedCornerShapeOutlinedButton(
            stringResource(id = R.string.change_playlist),
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = { }

        )
    }

}


@Preview
@Composable
internal fun PrimaryPlayListScreenPreview() {
    PlaylistScreenComponent(
        topBarSlot = {
            ColorPlaylistHeaderRow(colorModel = ColorModel(1, "#ffee11", "오렌지"))
        },
        playlist = listOf(),
        playlistId = 1,
        itemClick = { id, playPossible -> },
        itemLongClick = { id -> },
        moreIconClick = { item -> },
        onAddAudio = { id -> },
        onEditPlaylist = { id -> },
        onSearchAudioInList = { word -> }
    )

}
