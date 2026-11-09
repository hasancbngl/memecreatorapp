package com.hasancbngl.memecreatorapp.meme_editor.data

import android.content.Context
import androidx.compose.ui.unit.IntSize
import com.hasancbngl.memecreatorapp.meme_editor.domain.MemeExporter
import com.hasancbngl.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import com.hasancbngl.memecreatorapp.meme_editor.presentation.MemeText
import com.hasancbngl.memecreatorapp.meme_editor.presentation.util.MemeRenderCalculator

actual class PlatformMemeExporter(
    private val context: Context
) : MemeExporter {

    private val memeRenderCalculator = MemeRenderCalculator(
        density = context.resources.displayMetrics.density
    )

    actual override suspend fun exportMeme(
        backgroundImageBytes: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String
    ): Result<String> {
        TODO("Not yet implemented")
    }
}