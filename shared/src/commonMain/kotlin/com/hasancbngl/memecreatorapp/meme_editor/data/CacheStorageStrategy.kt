package com.hasancbngl.memecreatorapp.meme_editor.data

import com.hasancbngl.memecreatorapp.meme_editor.domain.SaveToStorageStrategy

expect class CacheStorageStrategy : SaveToStorageStrategy {
    override fun getFilePath(fileName: String): String
}