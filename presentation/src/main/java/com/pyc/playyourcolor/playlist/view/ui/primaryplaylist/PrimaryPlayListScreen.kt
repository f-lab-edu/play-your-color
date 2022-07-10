package com.pyc.playyourcolor.playlist.view.ui.primaryplaylist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pyc.playyourcolor.model.AudioModel
import com.pyc.playyourcolor.model.AudioPlaylistItemModel
import com.pyc.playyourcolor.model.ColorModel
import com.pyc.playyourcolor.playlist.view.ui.components.ColorListRow
import com.pyc.playyourcolor.playlist.view.ui.components.PlaylistScreenComponent


@Composable
internal fun PrimaryPlayListScreen(
    colorList: List<ColorModel>,
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
    //임시
    val selectedColorId: Int? = 1

    PlaylistScreenComponent(
        topBarSlot = {
            ColorListRow(
                colorList,
                selectedColorId,
                onAllSelect = {},
                onColorSelect = {})
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


@Preview
@Composable
internal fun PrimaryPlayListScreenPreview() {
    PlaylistScreenComponent(
        topBarSlot = {
            ColorListRow(
                colorList = listOf(
                    ColorModel(1, "#ffee11", "오렌지"),
                    ColorModel(2, "#faaa1d", "레드"),
                    ColorModel(3, "#ddffaa", "블랙"),
                    ColorModel(4, "#ccdd56", "갈색"),
                    ColorModel(5, "#7a7aff", "화이트"),
                    ColorModel(6, "#9d9d33", "노란색"),
                ),
                1,
                onAllSelect = {},
                onColorSelect = {})
        },
        playlist = listOf(
            AudioPlaylistItemModel(
                id = 1,
                audio = AudioModel(
                    "test",
                    "TT",
                    "트와이스",
                    300000,
                    "https://i.imgur.com/93QXZlj.png",
                    "mp3"
                )
            ),
            AudioPlaylistItemModel(
                id = 1, audio = AudioModel(
                    "test",
                    "라라라",
                    "SG워너비",
                    200000,
                    "https://i.imgur.com/93QXZlj.png",
                    "mp3",
                    playPossible = false
                )
            ),
            AudioPlaylistItemModel(
                id = 1, audio = AudioModel(
                    "test",
                    "LUPIN",
                    "DKZ",
                    180000,
                    "https://i.imgur.com/93QXZlj.png",
                    "mp3"
                )
            )
        ),
        playlistId = 1,
        itemClick = { id, playPossible -> },
        itemLongClick = { id -> },
        moreIconClick = { item -> },
        onAddAudio = { id -> },
        onEditPlaylist = { id -> },
        onSearchAudioInList = { word -> }
    )

}

