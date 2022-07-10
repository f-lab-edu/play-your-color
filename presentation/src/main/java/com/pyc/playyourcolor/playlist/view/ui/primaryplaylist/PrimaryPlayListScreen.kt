package com.pyc.playyourcolor.playlist.view.ui.primaryplaylist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.model.ColorModel
import com.pyc.playyourcolor.playlist.view.ui.components.ColorListRow
import com.pyc.playyourcolor.playlist.view.ui.components.PlaylistScreenComponent


@Composable
internal fun PrimaryPlayListScreen(
    colorList: List<ColorModel>,
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    nowPlayingAudioId: Int?,
    itemClick: (Int, Boolean) -> Unit,
    itemLongClick: (Int) -> Unit,
    moreIconClick: (AudioPlaylistItemModel) -> Unit,
    onAddAudio: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onSearchAudioInList: (String) -> Unit
) {
    //임시
    val selectedColorId: Int? = 1

    PlaylistScreenComponent(
        topBarSlot = {
            ColorListRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.item_padding_horizontal),
                        vertical = dimensionResource(id = R.dimen.item_padding_vertical)
                    ),
                colorList,
                selectedColorId,
                onAllSelect = {},
                onColorSelect = {})
        },
        playlist = playlist,
        playlistId = playlistId,
        nowPlayingAudioId = nowPlayingAudioId,
        itemClick = itemClick,
        itemLongClick = itemLongClick,
        moreIconClick = moreIconClick,
        onAddAudio = onAddAudio,
        onEditPlaylist = onEditPlaylist,
        onSearchAudioInList = onSearchAudioInList


    )
}



