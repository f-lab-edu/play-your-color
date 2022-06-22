package com.pyc.playyourcolor.playlist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pyc.playyourcolor.playlist.view.ui.colorplaylist.ColorPlayListScreen
import com.pyc.playyourcolor.playlist.view.ui.components.PlayListTabRow
import com.pyc.playyourcolor.playlist.view.ui.primaryplaylist.PrimaryPlayListScreen
import com.pyc.playyourcolor.playlist.view.ui.settings.SettingsScreen
import com.pyc.playyourcolor.playlist.view.ui.theme.PlayYourColorTheme
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
    PlayYourColorTheme {
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
            PrimaryPlayListScreen()
        }
        composable(PlayListScreen.Color.name) {
            ColorPlayListScreen()
        }
        composable(PlayListScreen.Settings.name) {
            SettingsScreen()
        }
    }
}