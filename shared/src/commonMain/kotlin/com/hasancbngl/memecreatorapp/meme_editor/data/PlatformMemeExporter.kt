package com.hasancbngl.memecreatorapp.meme_editor.data

import androidx.compose.ui.unit.IntSize
import com.hasancbngl.memecreatorapp.meme_editor.domain.MemeExporter
import com.hasancbngl.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import com.hasancbngl.memecreatorapp.meme_editor.presentation.MemeText

expect class PlatformMemeExporter : MemeExporter {
    override suspend fun exportMeme(
        backgroundImageBytes: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String
    ): Result<String>

}