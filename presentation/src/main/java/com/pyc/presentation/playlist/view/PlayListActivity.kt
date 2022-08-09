package com.pyc.presentation.playlist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pyc.presentation.model.AudioModel
import com.pyc.presentation.model.AudioPlaylistItemModel
import com.pyc.presentation.model.ColorModel
import com.pyc.presentation.model.PlayListItemAudioState
import com.pyc.presentation.playlist.view.ui.colorplaylist.ColorPlayListScreen
import com.pyc.presentation.playlist.view.ui.components.PlayListTabRow
import com.pyc.presentation.playlist.view.ui.primaryplaylist.PrimaryPlayListScreen
import com.pyc.presentation.playlist.view.ui.settings.SettingsScreen
import com.pyc.presentation.playlist.view.ui.theme.PlayYourColorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayYourColorApp()
        }
    }
}

@Composable
fun PlayYourColorApp() {
    PlayYourColorTheme(darkTheme = true) {
        val allScreens = PlayListScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = PlayListScreen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                PlayListTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NaveHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun NaveHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = PlayListScreen.Primary.name,
        modifier = modifier
    ) {
        composable(PlayListScreen.Primary.name) {


            PrimaryPlayListScreen(
                colorList = listOf(
                    ColorModel(1, "#ffee11", "오렌지"),
                    ColorModel(2, "#faaa1d", "레드"),
                    ColorModel(3, "#ddffaa", "블랙"),
                    ColorModel(4, "#ccdd56", "갈색"),
                    ColorModel(5, "#7a7aff", "화이트"),
                    ColorModel(6, "#9d9d33", "노란색"),
                ),
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
                        ),
                        PlayListItemAudioState.CanNotPlayAudio
                    ),

                    AudioPlaylistItemModel(
                        id = 1, audio = AudioModel(
                            "test",
                            "라라라",
                            "SG워너비",
                            200000,
                            "https://i.imgur.com/93QXZlj.png",
                            "mp3",
                        ),
                        PlayListItemAudioState.NowPlaying
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
                nowPlayingAudioId = 0,
                playlistId = 1,
                itemClick = { id, playPossible -> },
                itemLongClick = { id -> },
                moreIconClick = { item -> },
                onAddAudio = { id -> },
                onEditPlaylist = { id -> },
                onSearchAudioInList = { word -> }
            )
        }
        composable(PlayListScreen.Color.name) {
            ColorPlayListScreen(
                colorInfo = ColorModel(1, "#ffee11", "오렌지"),
                playlist = listOf(),
                playlistId = 1,
                nowPlayingAudioId = null,
                itemClick = { id, playPossible -> },
                itemLongClick = { id -> },
                moreIconClick = { item -> },
                onAddAudio = { id -> },
                onEditPlaylist = { id -> },
                onSearchAudioInList = { word -> },
                onChangeColorList = {}
            )
        }
        composable(PlayListScreen.Settings.name) {
            SettingsScreen()
        }
    }
}