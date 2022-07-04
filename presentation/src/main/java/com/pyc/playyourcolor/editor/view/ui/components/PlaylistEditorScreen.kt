package com.pyc.playyourcolor.editor.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pyc.playyourcolor.R
import com.pyc.playyourcolor.common.expand
import com.pyc.playyourcolor.common.move
import com.pyc.playyourcolor.common.noRippleClickable
import com.pyc.playyourcolor.editor.model.AudioEditModel
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaylistEditorScreen(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    items: MutableList<AudioEditModel>,
    title: @Composable (modifier: Modifier) -> Unit,
    topMenu: @Composable () -> Unit,
    bottomMenu: @Composable () -> Unit,
    onCompleted: (Int) -> Unit,
    onItemClicked: (Int) -> Unit,
    onMove: (Int, Int) -> Unit,
) {

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            bottomMenu()
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
                title(modifier = Modifier.align(Alignment.Center))
                ClickableText(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
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
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
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
                        .noRippleClickable{ onItemClicked(index) },
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


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PrimaryPlaylistEditor() {

    // dummy data 
    val ReorderItem = listOf(
        AudioEditModel(1, "", "처음 그 자리에", "이보람", "https://picsum.photos/id/237/200/300", true),
        AudioEditModel(2, "", "파도", "유엔", "", true),
        AudioEditModel(3, "", "Stand by You", "래디", "", true),
        AudioEditModel(4, "", "처음 그 자리에", "이보람", "", true),
    ).toMutableStateList()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    PlaylistEditorScreen(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        items = ReorderItem,
        title = {
            Text(
                modifier = it,
                text = stringResource(id = R.string.primary_playlist_editor),
                color = MaterialTheme.colors.onBackground
            )
        },
        topMenu = {
            Box(Modifier
                .fillMaxWidth()
            ){
                Row(modifier = Modifier
                    .align(Alignment.CenterStart)
                ) {
                    EditorPlusAudioButton {
                        // todo: 곡 추가화면 띄우기
                    }
                }
                EditorAllChoiceButton(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    // todo: 전체 선택 처리
                }
            }
        },
        bottomMenu = {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                EditorBottomIconButton(
                    imageVector = Icons.Filled.Add,
                    text = stringResource(id = R.string.add)
                ) {
                    // todo: 담기 화면
                }

                EditorBottomIconButton(
                    imageVector = Icons.Filled.Delete,
                    text = stringResource(id = R.string.delete)
                ) {
                    // todo: 삭제 처리
                }

                EditorBottomIconButton(
                    imageVector = Icons.Filled.Cancel,
                    text = stringResource(id = R.string.cancel)
                ) {
                    // todo: 선택 취소 처리
                }
            }
        },
        onCompleted = { _ ->
            // todo: 뒤로가기
        },
        onItemClicked = { idx ->
            // todo: viewmodel에 상태 처리
            bottomSheetScaffoldState.expand(coroutineScope)
        },
        onMove = { from, to ->
            // todo: viewmodel에 순서 변경 업데이트
            // onMove 말고 drag end 일떄 업데이트?
            ReorderItem.move(from, to)
        }
    )
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
    isChecked: Boolean = false,
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
            tint = if (isChecked) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
        )
        Text(
            text = stringResource(id = R.string.all_choice),
            color = if (isChecked) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
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