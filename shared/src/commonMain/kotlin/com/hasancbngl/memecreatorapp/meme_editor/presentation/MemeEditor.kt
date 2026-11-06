package com.hasancbngl.memecreatorapp.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.Image
import com.hasancbngl.memecreatorapp.core.presentation.MemeTemplate
import com.hasancbngl.memecreatorapp.core.presentation.memeTemplates
import com.hasancbngl.memecreatorapp.core.theme.MemeCreatorTheme
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(template.drawable),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        state.memeTexts.forEach { memeText ->
            MemeTextBox(
                memeText = memeText,
                textBoxInteractionState = state.textBoxInteractionState,
                maxWidth = 500.dp,
                maxHeight = 500.dp,
                onClick = {
                    onAction(MemeEditorAction.OnSelectMemeText(memeText.id))
                },
                onDoubleClick = {
                    onAction(MemeEditorAction.OnEditMemeText(memeText.id))
                },
                onTextChange = {
                    onAction(MemeEditorAction.OnMemeTextChange(memeText.id, it))
                },
                onDeleteClick = {
                    onAction(MemeEditorAction.OnDeleteMemeTextClick(memeText.id))
                }
            )
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