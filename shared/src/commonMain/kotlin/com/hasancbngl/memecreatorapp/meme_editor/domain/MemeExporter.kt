package com.hasancbngl.memecreatorapp.meme_editor.domain

import androidx.compose.ui.unit.IntSize
import com.hasancbngl.memecreatorapp.meme_editor.presentation.MemeText
import kotlin.uuid.Uuid

interface MemeExporter {
    suspend fun exportMeme(
        backgroundImageBytes: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String = "meme_${Uuid.random()}.jpg"
    ): Result<String>
}