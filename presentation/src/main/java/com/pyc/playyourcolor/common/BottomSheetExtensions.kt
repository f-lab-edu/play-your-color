package com.pyc.playyourcolor.common

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetScaffoldState.expand(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        if (bottomSheetState.isCollapsed) {
            bottomSheetState.expand()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetScaffoldState.collapse(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        if (bottomSheetState.isExpanded) {
            bottomSheetState.collapse()
        }
    }
}