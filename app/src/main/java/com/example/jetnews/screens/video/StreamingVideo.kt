package com.example.jetnews.screens.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetnews.model.Video
import com.example.jetnews.ui.video.TopVideoSection
import com.example.jetnews.ui.video.ExploreMoreVideoSection
import com.example.jetnews.ui.video.NewsVideoSection
import com.example.jetnews.ui.video.ScienceAndHealthSection
import com.example.jetnews.ui.video.SportVideoSection
import com.example.jetnews.utils.formatSystemTime
import com.example.jetnews.utils.getCurrentTimeMillis

@Composable
fun StreamingVideo(
    isPlaying: MutableState<Boolean>,
    videoItemIndex: MutableIntState,
    videoList: List<Video>,
    viewModel: VideoPlayerViewModel,
    navController:NavController,
    videoViewModel: VideoViewModel) {
    val currentTimeMills = getCurrentTimeMillis()
    val currentTime = formatSystemTime(currentTimeMills)

    Column {
        //TopVideoSection
        TopVideoSection(videoItemIndex = videoItemIndex,
            videoList = videoList,
            currentTime = currentTime
            )
        }

    //Explore more
        ExploreMoreVideoSection(
            videoList = videoList,
            currentTime = currentTime,
            viewModel = viewModel,
            videoItemIndex = videoItemIndex,
            isPlaying = isPlaying,
            videoViewModel = videoViewModel
        )
    CustomDivider()
    //News
    NewsVideoSection(
        videoList = videoList,
        currentTime = currentTime,
        viewModel = viewModel,
        videoItemIndex = videoItemIndex,
    )

    CustomDivider()

    //Sport
    SportVideoSection(
        videoList = videoList,
        viewModel =viewModel ,
        videoItemIndex =videoItemIndex )

    CustomDivider()

    //Science and health
    ScienceAndHealthSection(videoList = videoList,
        viewModel = viewModel,
        videoItemIndex = videoItemIndex,
        navController = navController)
}

@Composable
fun CustomDivider(){
    Spacer(modifier = Modifier.height(30.dp))
    Box(modifier = Modifier.padding(horizontal = 4.dp)){
        HorizontalDivider(thickness = 2.dp, color = Color.White)
    }
}