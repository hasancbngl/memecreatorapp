package com.hasancbngl.memecreatorapp.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.Image
import com.hasancbngl.memecreatorapp.core.presentation.MemeTemplate
import com.hasancbngl.memecreatorapp.core.presentation.memeTemplates
import com.hasancbngl.memecreatorapp.core.theme.MemeCreatorTheme
import com.hasancbngl.memecreatorapp.meme_editor.presentation.components.BottomBar
import com.hasancbngl.memecreatorapp.meme_editor.presentation.components.DraggableContainer
import com.hasancbngl.memecreatorapp.meme_editor.presentation.components.MemeTextBox
import memecreatorapp.shared.generated.resources.Res
import memecreatorapp.shared.generated.resources.meme_template_01
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemeEditorRoot(
    template: MemeTemplate,
    onGoBack: () -> Unit,
    viewModel: MemeEditorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MemeEditorScreen(
        state = state,
        onAction = viewModel::onAction,
        template = template
    )
}

@Composable
fun MemeEditorScreen(
    template: MemeTemplate,
    state: MemeEditorState,
    onAction: (MemeEditorAction) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    onAction(MemeEditorAction.OnTapOutsideSelectedText)
                }
            },
        bottomBar = {
            BottomBar(
                onAddTextClick = {
                    onAction(MemeEditorAction.OnAddTextClick)
                },
                onSaveClick = {
                    onAction(MemeEditorAction.OnSaveMemeClick(template))
                },

                )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box {
                Image(
                    painter = painterResource(template.drawable),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                        .onSizeChanged {
                            onAction(MemeEditorAction.OnContainerSizeChange(it))
                        }
                )
                DraggableContainer(
                    children = state.memeTexts,
                    textBoxInteractionState = state.textBoxInteractionState,
                    onChildTransformChange = { id, offset, rotation, scale ->
                        onAction(
                            MemeEditorAction.OnMemeTextTransformChange(
                                id = id,
                                offset = offset,
                                rotation = rotation,
                                scale = scale
                            )
                        )
                    },
                    onChildClick = {
                        onAction(MemeEditorAction.OnSelectMemeText(it))
                    },
                    onChildDoubleClick = {
                        onAction(MemeEditorAction.OnEditMemeText(it))
                    },
                    onChildTextChange = { id, text ->
                        onAction(MemeEditorAction.OnMemeTextChange(id, text))
                    },
                    onChildDeleteClick = {
                        onAction(MemeEditorAction.OnDeleteMemeTextClick(it))
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MemeCreatorTheme {
        MemeEditorScreen(
            state = MemeEditorState(),
            onAction = {},
            template = MemeTemplate(
                "meme_template_01",
                Res.drawable.meme_template_01
            )
        )
    }
}