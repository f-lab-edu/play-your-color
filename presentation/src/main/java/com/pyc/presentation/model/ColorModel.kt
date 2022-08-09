package com.pyc.presentation.model

import androidx.compose.ui.graphics.Color

internal class ColorModel(
    val id: Int,
    private val hexColor: String, //hex code
    val name: String
) {
    val color: Color = Color(android.graphics.Color.parseColor(hexColor))
}