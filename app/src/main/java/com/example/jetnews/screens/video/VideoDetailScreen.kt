package com.example.jetnews.screens.video

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jetnews.data.video.videoList
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.ui.video.ExploreMoreVideoSection
import com.example.jetnews.utils.dateLocaleFromDatabase
import com.example.jetnews.utils.formatSystemTime
import com.example.jetnews.utils.getCurrentTimeMillis
import com.example.jetnews.utils.timePeriodCalculator

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoDetailScreen(
    navController: NavController,
    videoPlayerViewModel: VideoPlayerViewModel = viewModel(),
    videoId: String? = "1"
){
    val isFullScreen = videoPlayerViewModel.isInFullScreenMode
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            if (!isFullScreen){
                TopAppBar(title = {},
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription ="arrow back icon",
                                modifier = Modifier.clickable {
                                    navController.popBackStack()
                                })
                            Text(text = "Back", style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier.clickable {
                                    navController.popBackStack()
                                })
                        }

                    },
                    actions = {
                        Row(modifier = Modifier.clickable {
                            //navigate to share
                        }, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Share", style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 4.dp))
                            Icon(imageVector = Icons.Default.Share, contentDescription = "share icon",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(modifier = Modifier.clickable {
                            //save to list
                        }, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Save",style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 2.dp))
                            Icon(imageVector = Icons.Default.Add, contentDescription = "add icon",
                                tint = Color.White)
                        }

                        IconButton(onClick = {
                            navController.navigate(NewsScreen.VideoScreen.name)
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription ="close icon",
                                tint = Color.White)

                        }
                    },
                    colors = TopAppBarColors(
                        containerColor = Color.Black,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        titleContentColor = Color.Black,
                        scrolledContainerColor = Color.Black
                    )
                )
            }else{

            }

        }
    ){contentPadding->
        VideoDetailContent(modifier = Modifier.padding(contentPadding),
            viewModel = videoPlayerViewModel,
            videoId = videoId,
            isFullScreen = isFullScreen
        )

    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun VideoDetailContent(
    modifier: Modifier,
    viewModel: VideoPlayerViewModel,
    videoId: String?,
    isFullScreen:Boolean) {
    var isPlaying by remember {
        mutableStateOf(false)
    }
    viewModel.videoList = videoList
    Log.d("videoList",videoList.toString())
    val videoIndexId = videoId?.toInt()?.minus(1)
    val video = videoList[videoIndexId!!]
    Log.d("video detail",video.toString())
    val videoTopic = video.topic
    val context = LocalContext.current

    val systemTime = getCurrentTimeMillis()
    val currentTime = formatSystemTime(systemTime)
    val createdTime = video.createTime
    val dateLocale = dateLocaleFromDatabase(createdTime)

    LaunchedEffect(key1 = videoIndexId) {
        isPlaying = true
        viewModel.apply{
            releasePlayer()
            initializePlayer(context)
            playVideo()
        }
    }

    if (!isFullScreen){
        LazyColumn(modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Black)) {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                ){
                    StreamerPlayer(viewModel = viewModel, isPlaying = isPlaying) {

                    }
                }
            }

            item {
                Text(text = video.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
                
                Text(text = video.description,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
                
                Text(text = dateLocale,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Box(modifier = Modifier
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = Color(0xFF393C3E)),
                        //.padding(horizontal = 8.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center){
                        Text(text = "Science & Health", color = Color.White,
                            style = MaterialTheme.typography.labelSmall)
                    }
                }

            }

            item {
                val videoTopicRelatedList = videoList.filter { item->
                    item.topic == videoTopic
                }
//                ExploreMoreVideoSection(
//                    videoList = videoTopicRelatedList,
//                    currentTime = currentTime,
//                    viewModel = viewModel,
//                    videoItemIndex =
//                )
                Column {
                    Text(text = "Explore more",
                        modifier = Modifier.padding(vertical = 4.dp,horizontal = 2.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold)

                    LazyRow {
                        itemsIndexed(items = videoTopicRelatedList){index,item ->
                            val createdTime = item.createTime
                            val timePeriod = timePeriodCalculator(currentTime,createdTime)
                            Column(
                                modifier = Modifier
                                    .width(130.dp)
                                    .padding(2.dp)
                                    .clickable {
                                        //become the main video to play
//                                        viewModel.index = index
//                                        videoItemIndex.value = viewModel.index


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
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                    }


                }
            }


        }
    }else{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            StreamerPlayer(viewModel = viewModel, isPlaying = isPlaying){isVideoPlaying ->
                isPlaying = isVideoPlaying}
        }
    }




}
