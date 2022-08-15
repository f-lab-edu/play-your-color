package com.pyc.presentation.playlist.view.ui.colorplaylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.pyc.presentation.R
import com.pyc.presentation.model.*
import com.pyc.presentation.model.AudioPlaylistItemModel
import com.pyc.presentation.model.ColorListItemModel
import com.pyc.presentation.model.ColorModel
import com.pyc.presentation.playlist.view.ui.components.ColorPlayListTabColorList
import com.pyc.presentation.playlist.view.ui.components.PlaylistScreenComponent
import com.pyc.presentation.playlist.view.ui.components.RoundedCornerShapeOutlinedButton

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

    val (screenMode, setScreenMode) = remember {
        mutableStateOf<ColorPlaylistScreen>(Playlist)
    }

    val colorList = listOf<ColorListItemModel>(

        ColorListItemModel(ColorModel(1, "#ffee11", "오렌지"), 10),
        ColorListItemModel(ColorModel(2, "#faaa1d", "레드"), 1),
        ColorListItemModel(ColorModel(3, "#ddffaa", "블랙"), 20),
        ColorListItemModel(ColorModel(4, "#ccdd56", "갈색"), 51),
        ColorListItemModel(ColorModel(5, "#7a7aff", "화이트"), 18),
        ColorListItemModel(ColorModel(6, "#9d9d33", "노란색"), 33),
    )

    when (screenMode) {
        is ColorList -> ColorPlayListTabColorList(colorList = colorList, colorClickListener = {setScreenMode(Playlist)})
        is Playlist ->
            PlaylistScreenComponent(
            topBarSlot = {
                ColorPlaylistHeaderRow(colorInfo = colorInfo, onChangeColorList = {setScreenMode(ColorList)})
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
