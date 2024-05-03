package com.example.jetnews.ui.video

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnews.model.Video
import com.example.jetnews.utils.formatSystemTime
import com.example.jetnews.utils.getCurrentTimeMillis
import com.example.jetnews.utils.timePeriodCalculator


@Composable
fun TopVideoSection(videoItemIndex: MutableIntState,
                    videoList:List<Video>,
                    currentTime:String) {
    val currentPlayingVideo:Video = videoList[videoItemIndex.intValue]
    Log.d("current video data", videoList[videoItemIndex.intValue].toString())
    val createdTime = currentPlayingVideo.createTime
    val timePeriod = timePeriodCalculator(currentTime,createdTime)
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 2.dp)
            .background(color = Color.Black)
    ) {
        Text(text = currentPlayingVideo.title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White)
        Text(text = currentPlayingVideo.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            lineHeight = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp))
        Text(text = timePeriod,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Share",color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .clickable {
                        //jump to related outside links
                    })
            Spacer(modifier = Modifier.width(4.dp))
            Icon(imageVector = Icons.Default.Share, contentDescription = "share icon",
                tint = Color.White,
                modifier = Modifier.size(15.dp).clickable {
                    //share link to others
                })
        }
    }
}