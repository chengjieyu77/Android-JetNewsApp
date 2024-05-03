package com.example.jetnews.screens.video

import android.app.PictureInPictureParams
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetnews.R
import com.example.jetnews.data.video.videoList
import com.example.jetnews.navigation.NewsScreen


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun VideoScreen(navController: NavController,
                videoPlayerViewModel: VideoPlayerViewModel = viewModel(),
                videoViewModel: VideoViewModel = hiltViewModel()
){
    val isFullScreen = videoPlayerViewModel.isInFullScreenMode
    VideoScaffold(navController = navController,
        isFullScreen=isFullScreen,
        videoPlayerViewModel = videoPlayerViewModel,
        videoViewModel = videoViewModel)
    Log.d("isfullscreen","$isFullScreen")

}


@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoScaffold(navController: NavController,
                  isFullScreen:Boolean,
                  videoPlayerViewModel: VideoPlayerViewModel,
                  videoViewModel: VideoViewModel
                  ) {
                var selectedNavigationItem by remember {
                mutableStateOf(1)
            }
    //var isInFullScreenMode by remember{ mutableStateOf(false) }

    Scaffold(
        topBar = {
            if(isFullScreen == false){
                TopAppBar(title = { Text(text = "Video", color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 30.sp, fontWeight = FontWeight.Bold)},
                    actions = {
                        Text(text = "Sign in",modifier = Modifier.clickable {
                            Log.d("sign in text","route to sign in screen")
                        })
                        IconButton(onClick = { Log.d("person icon","route to sign in screen") }) {
                            Icon(imageVector = Icons.Filled.Person, contentDescription ="person icon",
                                tint = MaterialTheme.colorScheme.onSurface)
                        }
                    })
            }

        },
        bottomBar = {
            if (isFullScreen == false){
                val navigationItems = listOf(
                    stringResource(id = R.string.bottom_bar_title_home),
                    stringResource(id = R.string.bottom_bar_title_video),
                    stringResource(id = R.string.bottom_bar_title_live),
                    stringResource(id = R.string.bottom_bar_title_more)
                )
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ) {

                    navigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(selected = index == selectedNavigationItem,
                            onClick = {
                                when(index){
                                    0 -> navController.navigate(NewsScreen.HomeScreen.name)
                                    1 -> navController.navigate(NewsScreen.VideoScreen.name)
                                    2 -> navController.navigate(NewsScreen.LiveScreen.name)
                                    3 -> navController.navigate(NewsScreen.MoreScreen.name)
                                }
                            },
                            icon = {
                                when(index){
                                    0 -> Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = stringResource(
                                        id = R.string.bottom_bar_cd_home),
                                        modifier = Modifier.size(25.dp))
                                    1 -> Icon(painter = painterResource(id = R.drawable.ic_video), contentDescription = stringResource(
                                        id = R.string.bottom_bar_cd_video),
                                        modifier = Modifier.size(25.dp))
                                    2 -> Icon(painter = painterResource(id = R.drawable.ic_live), contentDescription = stringResource(
                                        id = R.string.bottom_bar_cd_live),
                                        modifier = Modifier.size(25.dp))
                                    3 -> Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = stringResource(
                                        id = R.string.bottom_bar_cd_more),
                                        modifier = Modifier.size(25.dp))
                                }
                            },
                            label = { Text(text = item)})
                    }

                }
            }

        }
    ) {contentPadding ->
        VideoContent(modifier = Modifier.padding(contentPadding),
            isFullScreen = isFullScreen,
            videoPlayerViewModel = videoPlayerViewModel,
            navController = navController,
            videoViewModel = videoViewModel
            )
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun VideoContent(modifier: Modifier,isFullScreen: Boolean,
                 videoPlayerViewModel: VideoPlayerViewModel,
                 navController: NavController,
                 videoViewModel: VideoViewModel
                 ) {
    var isPlaying = remember{
        mutableStateOf(false)
    }
    val videoItemIndex = remember{
        mutableIntStateOf(0)
    }
    val viewModel=videoPlayerViewModel
    viewModel.videoList = videoList
    val context = LocalContext.current

    LaunchedEffect(key1 = videoItemIndex.intValue) {
        isPlaying.value = true
        viewModel.apply{
            releasePlayer()
            initializePlayer(context)
            playVideo()
        }
    }
    if (isFullScreen == false){
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                ){
                    StreamerPlayer(viewModel = viewModel, isPlaying = isPlaying.value) {

                    }
                }
            }
            item {
                StreamingVideo(
                    isPlaying = isPlaying,
                    videoItemIndex = videoItemIndex,
                    videoList = videoList,
                    viewModel = videoPlayerViewModel,
                    navController = navController,
                    videoViewModel = videoViewModel
                )
            }
        }
    }else{
        Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        StreamerPlayer(viewModel = viewModel, isPlaying = isPlaying.value){isVideoPlaying ->
                            isPlaying.value = isVideoPlaying}
                    }
    }
}
@Composable
fun PipListenerPreAPI12(shouldEnterPipMode: Boolean) {
    // Using the rememberUpdatedState ensures that the updated version of shouldEnterPipMode is
    // used by the DisposableEffect
    val currentShouldEnterPipMode by rememberUpdatedState(newValue = shouldEnterPipMode)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
        Build.VERSION.SDK_INT < Build.VERSION_CODES.S
    ) {
        val context = LocalContext.current
//        DisposableEffect(context) {
//
//        }
        val activity = LocalContext.current.findActivity()
        DisposableEffect(activity) {
            val onUserLeaveBehavior = {
                if (currentShouldEnterPipMode) {
                    activity.enterPictureInPictureMode(PictureInPictureParams.Builder().build())
                }
            }
//            activity.addOnUserLeaveHintListener(
//                onUserLeaveBehavior,
//            )
            onDispose {
//                activity.removeOnUserLeaveHintListener(
//                    onUserLeaveBehavior,
//                )
            }
        }
    }
}


internal fun Context.findActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Picture in picture should be called in the context of an Activity")
}















