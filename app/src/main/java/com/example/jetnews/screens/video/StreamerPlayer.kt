package com.example.jetnews.screens.video

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.content.ContextWrapper
import android.media.AudioManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun StreamerPlayer(
    viewModel: VideoPlayerViewModel,
    isPlaying:Boolean,
    onPlayerClosed:(isVideoPlaying:Boolean)->Unit
){
    Box(
        modifier = Modifier
            .aspectRatio(16f / 9f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (isPlaying) {

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth(),
                factory = { cont ->
                    viewModel.playerViewBuilder((cont))
                })
            IconButton(onClick = { onPlayerClosed.invoke(false)
                viewModel.releasePlayer()},
                modifier = Modifier.align(Alignment.TopEnd),) {
                Icon(
                    imageVector = Icons.Default.Close, contentDescription = null,
                    tint = Color.White
                )
            }
//            Box(modifier = Modifier
//                .align(Alignment.BottomStart)
//                .padding(start = 80.dp)
//
//                ){
//                AudioSlider()
//            }




        }else{
            Text(text = "loading")
        }

    }
}


@Composable
fun AudioSlider(context:Context = LocalContext.current){
    val audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val volumePercent = (volumeLevel.toFloat() / maxVolumeLevel * 100).toInt()

    var currentVolume by remember{
        mutableStateOf(volumePercent.toFloat())
    }

    Slider(
        value = currentVolume,
        onValueChange ={currentVolume = it},
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.Gray
        ),
        modifier = Modifier.width(80.dp)
    )

 //   audioManager.adjustVolume(
//        AudioManager.ADJUST_RAISE,
//        AudioManager.ADJUST_LOWER,
//        AudioManager.FLAG_PLAY_SOUND
//    )

    Text(text = currentVolume.toString())



}

