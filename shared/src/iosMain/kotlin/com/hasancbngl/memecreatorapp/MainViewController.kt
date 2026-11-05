package com.hasancbngl.memecreatorapp

import androidx.compose.ui.window.ComposeUIViewController
import com.hasancbngl.memecreatorapp.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
){
    App()
}