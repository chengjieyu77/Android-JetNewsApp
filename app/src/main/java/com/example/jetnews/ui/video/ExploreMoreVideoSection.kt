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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetnews.components.VideoRow
import com.example.jetnews.model.Video
import com.example.jetnews.screens.video.VideoPlayerViewModel
import com.example.jetnews.screens.video.VideoViewModel
import com.example.jetnews.utils.timePeriodCalculator

@Composable
fun ExploreMoreVideoSection(
    videoList: List<Video>,
    currentTime: String,
    viewModel: VideoPlayerViewModel,
    videoItemIndex: MutableIntState,
    isPlaying: MutableState<Boolean>,
    videoViewModel: VideoViewModel
) {
    val videos = remember{
        mutableStateOf(videoList)
    }

    val arrayVideos = ArrayList(videoList)
    val arrayVideosState = remember {
        mutableStateOf(arrayVideos)
    }

    //Explore more
    Column {
        Text(text = "Explore more",
            modifier = Modifier.padding(vertical = 4.dp,horizontal = 2.dp),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold)

        LazyRow {
            itemsIndexed(items = arrayVideos){index,item ->
                val createdTime = item.createTime
                val timePeriod = timePeriodCalculator(currentTime,createdTime)
                if(index != videoItemIndex.value){
                    Column(
                        modifier = Modifier
                            .width(130.dp)
                            .padding(2.dp)
                            .clickable {
                                if(videoItemIndex.value != index){
                                    isPlaying.value = false
                                    viewModel.index = index
                                    val clickedVideo = videoList[videoItemIndex.value]
                                    val clickingVideo = videoList[index]
                                    val updatedVideoList =
                                        videoList.filterNot { it == clickedVideo } + clickedVideo
                                    videos.value = updatedVideoList
                                    videoItemIndex.value = getIndexById(updatedVideoList,clickingVideo.id.toString())
                                    videoViewModel.addVideo(clickingVideo)
                                }
                                //become the main video to play
//                                viewModel.index = index
//                                videoItemIndex.value = viewModel.index

                                //click 之后相应的视频会放在列表最后
                                arrayVideos.removeAt(index)
                                arrayVideos.add(videoList[index])
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
                            Text(text = "$timePeriod | ${item.topic}", style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterVertically),
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.width(8.dp))
            }

        }


    }
}

fun getIndexById(videoList: List<Video>,id:String):Int{
    for((index,video) in videoList.withIndex()){
        if(video.id == id){
            return index
        }
    }
    return -1
}