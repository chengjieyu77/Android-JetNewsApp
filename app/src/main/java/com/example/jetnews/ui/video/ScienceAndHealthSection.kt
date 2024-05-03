package com.example.jetnews.ui.video

import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jetnews.model.Video
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.screens.video.VideoPlayerViewModel
import com.example.jetnews.utils.timePeriodCalculator

@OptIn(UnstableApi::class)
@Composable
fun ScienceAndHealthSection(
    videoList: List<Video>,
    viewModel: VideoPlayerViewModel,
    videoItemIndex: MutableIntState,
    navController: NavController
){
    val healthVideoList = videoList.filter {video ->
        video.topic == "health"
    }
    val selectedHealthVideo = healthVideoList[0]
    val videoId = selectedHealthVideo.id

    Column{
        Column(modifier = Modifier
            .fillMaxWidth()
        .clickable {
            //become the main video to play
//            viewModel.index = selectedHealthVideo.id
//            videoItemIndex.intValue = viewModel.index
            Log.d("selected health video","${selectedHealthVideo.id}")
            Log.d("video item index","${videoItemIndex.intValue}")
            Log.d("selected video",selectedHealthVideo.toString())


        }) {
            Text(text = "Science and health",
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 2.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(model = selectedHealthVideo.thumbnail, contentDescription = "video thumb nail",
                    modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = selectedHealthVideo.title, style = MaterialTheme.typography.titleSmall,
                maxLines = 3,
                color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(onClick = {
            //navController.navigate(NewsScreen.VideoDetailScreen.name )
                         navController.navigate(NewsScreen.VideoDetailScreen.name)
                         },
            modifier = Modifier
                .width(90.dp)
                .height(40.dp),
            border = BorderStroke(width = 1.dp,color = Color.White),
            colors = ButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White
            ),
            shape = RectangleShape,
            contentPadding = PaddingValues(2.dp)

        ) {
            Text(text = "See More", fontSize = 13.sp,
                maxLines = 1)
        }

//        LazyRow(
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            itemsIndexed(items = videoList){index,item ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(2.dp)
//                        .clickable {
//                            //become the main video to play
//                            viewModel.index = index
//                            videoItemIndex.value = viewModel.index
//                        },
//                    verticalArrangement = Arrangement.Center,
//                    //horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    AsyncImage(model = item.thumbnail, contentDescription = "video thumb nail",
//                        modifier = Modifier.fillMaxWidth())
//                    Text(
//                        text = item.title, style = MaterialTheme.typography.titleSmall,
//                        maxLines = 3,
//                        color = Color.White)
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    Button(onClick = { /*TODO*/ },
//                            modifier = Modifier
//                                .width(90.dp)
//                                .height(40.dp),
//                            border = BorderStroke(width = 1.dp,color = Color.White),
//                            colors = ButtonColors(
//                                containerColor = Color.Black,
//                                contentColor = Color.White,
//                                disabledContainerColor = Color.Black,
//                                disabledContentColor = Color.White
//                            ),
//                        shape = RectangleShape,
//                        contentPadding = PaddingValues(2.dp)
//
//                        ) {
//                            Text(text = "See More", fontSize = 13.sp,
//                                maxLines = 1)
//                        }
//
//
//
//                }
//                Spacer(modifier = Modifier.width(8.dp))
//            }
//
//        }


    }
}