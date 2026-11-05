package com.hasancbngl.memecreatorapp.meme_editor.presentation

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class MemeText(
    val id: String,
    val text: String,
    val fontSize: TextUnit = 36.sp,
    val offSetRatioX: Float = 0f,
    val offSetRatioY: Float = 0f,
    val rotation: Float = 0f,
    val scale: Float = 1f
)
