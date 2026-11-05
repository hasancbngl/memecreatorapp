package com.hasancbngl.memecreatorapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hasancbngl.memecreatorapp.core.presentation.Navigation
import com.hasancbngl.memecreatorapp.core.theme.MemeCreatorTheme

@Composable
@Preview
fun App() {
    MemeCreatorTheme {
        Navigation()
    }
}