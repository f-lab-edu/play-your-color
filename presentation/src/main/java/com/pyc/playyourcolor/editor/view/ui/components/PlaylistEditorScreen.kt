package com.pyc.playyourcolor.editor.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.common.noRippleClickable
import com.pyc.playyourcolor.editor.model.AudioEditModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaylistEditorScreen(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    items: List<AudioEditModel>,
    title: String,
    topMenu: @Composable () -> Unit,
    bottomMenu: @Composable () -> Unit,
    onCompleted: (Int) -> Unit,
    onItemClicked: (Int) -> Unit,
    onMove: (Int, Int) -> Unit,
) {

    var size by remember { mutableStateOf(IntSize.Zero) }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            bottomMenu()
        },
        sheetPeekHeight = 0.dp,
        modifier = Modifier.onSizeChanged { size = it }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = title,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 20.sp
                )
                ClickableText(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontSize = 20.sp
                            )
                        ) {
                            append(stringResource(id = R.string.complete))
                        }
                    },
                    onClick = onCompleted
                )
            }
            topMenu()
            DragDropList(
                items = items,
                onMove = onMove,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 10.dp, start = 10.dp, end = 10.dp,
                        bottom = if (bottomSheetScaffoldState.bottomSheetState.isExpanded)
                            with(LocalDensity.current) {
                                (size.height - bottomSheetScaffoldState.bottomSheetState.offset.value).toDp()
                            }
                        else 0.dp)
                    .weight(1f),
                key = { _, item -> item.id }
            ) { index, item, offsetOrNull ->
                Row(
                    modifier = Modifier
                        .graphicsLayer {
                            translationY = offsetOrNull() ?: 0f
                        }
                        .background(
                            Color(1f, 1f, 1f, if (item.checked) 0.3f else 0f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .alpha(if (offsetOrNull() == null) 1f else 0.7f)
                        .zIndex(if (offsetOrNull() == null) 0f else 1f)
                        .noRippleClickable { onItemClicked(index) },
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    GlideImage(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        imageModel = item.imgUri.ifEmpty { null },
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.title,
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = item.artist,
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun EditorBottomIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = onClick,
        elevation = null
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = imageVector,
                contentDescription = text,
                tint = MaterialTheme.colors.onPrimary
            )
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun EditorAllChoiceButton(
    modifier: Modifier = Modifier,
    maxSelectedCount: Int = 0,
    selectedCount: Int = 0,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = onClick,
        elevation = null
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.all_choice),
            tint = if (maxSelectedCount == selectedCount) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
        )
        Text(
            text = stringResource(id = R.string.all_choice),
            color = if (maxSelectedCount == selectedCount) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun EditorPlusAudioButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = onClick,
        elevation = null
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.plus_song),
            tint = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(id = R.string.plus_song),
            color = MaterialTheme.colors.onPrimary
        )
    }
}