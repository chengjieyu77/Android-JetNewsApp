package com.example.jetnews.ui.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetnews.components.VideoRow
import com.example.jetnews.model.Video
import com.example.jetnews.screens.video.VideoPlayerViewModel
import com.example.jetnews.utils.timePeriodCalculator

@Composable
fun NewsVideoSection(
    videoList: List<Video>,
    currentTime: String,
    viewModel: VideoPlayerViewModel,
    videoItemIndex: MutableIntState
) {
    Column {
        Text(text = "News",
            modifier = Modifier.padding(vertical = 4.dp,horizontal = 2.dp),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold)

        LazyRow(modifier = Modifier.padding(top = 16.dp)) {
            itemsIndexed(items = videoList){index,item ->
                val createdTime = item.createTime
                val timePeriod = timePeriodCalculator(currentTime,createdTime)
                Column(
                    modifier = Modifier
                        .width(130.dp)
                        .padding(2.dp)
                        .clickable {
                            //become the main video to play
                            viewModel.index = index
                            videoItemIndex.value = viewModel.index
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(model = item.thumbnail, contentDescription = "video thumb nail",)
                    Text(
                        text = item.title, style = MaterialTheme.typography.titleSmall,
                        maxLines = 3,
                        color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start) {
                        Text(
                            buildAnnotatedString {
                                pushStyle(SpanStyle(color = Color.Gray,fontSize = 11.sp, letterSpacing = 0.5.sp, fontWeight = FontWeight.Medium))
                                append("$timePeriod | ")
                                pushStyle(SpanStyle(color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold,letterSpacing = 0.5.sp))
                                append("News ")
                                pushStyle(SpanStyle(color = Color.Gray,fontSize = 11.sp, letterSpacing = 0.5.sp, fontWeight = FontWeight.Medium))
                                append(item.topic)
                            }
                        )
//                        Text(
//                            text = "$timePeriod | ",
//                            style = MaterialTheme.typography.labelSmall,
//                            color = Color.Gray,
//                            modifier = Modifier.align(Alignment.CenterVertically),
//                        )
//                        Text(text = "News",
//                            style = MaterialTheme.typography.labelSmall,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold)
//                        Text(text = item.topic,
//                            style = MaterialTheme.typography.labelSmall,
//                            color = Color.Gray,
//                            modifier = Modifier.align(Alignment.CenterVertically),
//                            overflow = TextOverflow.Ellipsis)
                    }

                }
                Spacer(modifier = Modifier.width(8.dp))
            }

        }


    }
}