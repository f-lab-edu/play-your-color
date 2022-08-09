package com.pyc.presentation.playlist.view.ui.primaryplaylist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pyc.presentation.R
import com.pyc.presentation.model.AudioPlaylistItemModel
import com.pyc.presentation.model.ColorModel
import com.pyc.presentation.playlist.view.ui.components.ColorListRow
import com.pyc.presentation.playlist.view.ui.components.PlaylistScreenComponent


@Composable
internal fun PrimaryPlayListScreen(
    colorList: List<ColorModel>,
    playlist: List<AudioPlaylistItemModel>,
    playlistId: Int,
    nowPlayingAudioId: Int?,
    itemClick: (Int, AudioPlaylistItemModel) -> Unit,
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
        itemClick = itemClick,
        itemLongClick = itemLongClick,
        moreIconClick = moreIconClick,
        onAddAudio = onAddAudio,
        onEditPlaylist = onEditPlaylist,
        onSearchAudioInList = onSearchAudioInList


    )
}



