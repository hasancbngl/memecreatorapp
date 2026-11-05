package com.hasancbngl.memecreatorapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.hasancbngl.memecreatorapp.meme_editor.presentation.MemeEditorRoot
import com.hasancbngl.memecreatorapp.meme_gallery.presentation.MemeGalleryScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.MemeGallery
    ) {
        composable<Route.MemeGallery> {
            MemeGalleryScreen {
                navController.navigate(Route.MemeEditor(it.id))
            }

            composable<Route.MemeEditor> {
                val templateId = it.toRoute<Route.MemeEditor>().templateId
                val template = remember(templateId) {
                    memeTemplates.first { it.id == templateId }
                }

                MemeEditorRoot(
                    template = template,
                    onGoBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}