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
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.model.ColorModel
import com.pyc.playyourcolor.playlist.view.ui.components.PlaylistScreenComponent
import com.pyc.playyourcolor.playlist.view.ui.components.RoundedCornerShapeOutlinedButton

@Composable
internal fun ColorPlayListScreen(
    colorInfo: ColorModel,
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    nowPlayingAudioId: Int?,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
    onAddAudio: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onSearchAudioInList: (String) -> Unit,
    onChangeColorList: () -> Unit
) {
    PlaylistScreenComponent(
        topBarSlot = {
            ColorPlaylistHeaderRow(colorInfo = colorInfo, onChangeColorList = onChangeColorList)
        },
        playlist = playlist,
        playlistId = playlistId,
        itemClick = itemClick,
        itemLongClick = itemLongClick,
        moreIconClick = moreIconClick,
        onAddAudio = onAddAudio,
        onEditPlaylist = onEditPlaylist,
        onSearchAudioInList = onSearchAudioInList
    )

}



@Composable
internal fun ColorPlaylistHeaderRow(colorInfo: ColorModel, onChangeColorList: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.item_padding_horizontal))
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = colorInfo.name,
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )

        RoundedCornerShapeOutlinedButton(
            stringResource(id = R.string.change_playlist),
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = onChangeColorList

        )
    }

}
