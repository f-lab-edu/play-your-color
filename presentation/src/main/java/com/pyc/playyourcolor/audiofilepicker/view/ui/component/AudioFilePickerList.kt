package com.pyc.playyourcolor.audiofilepicker.view.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyc.playyourcolor.audiofilepicker.model.AudioFile
import com.pyc.playyourcolor.common.noRippleClickable
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AudioFilePickerList(
    modifier: Modifier = Modifier,
    audioList: List<AudioFile> = listOf(
        AudioFile(1, "", "붉은노을", "빅뱅","","m4a", 350),
        AudioFile(2, "", "붉은노을", "빅뱅","","m4a", 350)
    ),
    itemClicked: (Int, AudioFile) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(10.dp)
    ) {
        itemsIndexed(
            audioList,
            key = { _, item -> item.id }
        ) { index, item ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .noRippleClickable { itemClicked(index, item) }
                    .background(
                        Color(1f, 1f, 1f, if (item.selected) 0.3f else 0f),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    GlideImage(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        imageModel = item.thumbnail.ifEmpty {  }
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = item.title)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = item.artist)
                    }
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .align(Alignment.BottomEnd),
                    text = item.duration.toString()
                )
            }
        }
    }
}