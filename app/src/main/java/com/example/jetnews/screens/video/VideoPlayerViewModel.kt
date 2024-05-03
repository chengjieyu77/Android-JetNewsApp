package com.example.jetnews.screens.video

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Context
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsets.Type.navigationBars
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView
import com.example.jetnews.model.Video

class VideoPlayerViewModel :ViewModel(){
    private var exoPlayer: ExoPlayer? = null
    var index:Int = 0
    var videoList:List<Video> = listOf()
    var isInFullScreenMode:Boolean = false

    fun initializePlayer(context: Context){
        exoPlayer = ExoPlayer.Builder(context).build()
    }
    fun releasePlayer(){
        exoPlayer?.playWhenReady = false
        exoPlayer?.release()
        exoPlayer = null
    }
    fun playVideo(){
        exoPlayer?.let{player->
            player.apply {
                stop()
                clearMediaItems()
                setMediaItem(MediaItem.fromUri(Uri.parse(videoList[index].videoUrl)))
                playWhenReady = true
                prepare()
                play()
            }

        }
    }

    @OptIn(UnstableApi::class)
    @RequiresApi(Build.VERSION_CODES.R)
    fun playerViewBuilder(context: Context): PlayerView {
        val activity = context as Activity
        val playerView = PlayerView(context).apply{
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            player = exoPlayer
            useController = true
            controllerAutoShow = true
            keepScreenOn = true //keep the screen on when we are on the landscape mode
            setFullscreenButtonClickListener { isFullScreen ->
                if(isFullScreen){
                    activity.requestedOrientation = SCREEN_ORIENTATION_USER_LANDSCAPE
                    isInFullScreenMode = true
                }else{
                    activity.requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
                    isInFullScreenMode = false
                }
            }
        }
        return playerView
    }

}