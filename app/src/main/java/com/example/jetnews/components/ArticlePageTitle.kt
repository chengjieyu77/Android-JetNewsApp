package com.example.jetnews.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnews.R

@Preview
@Composable
fun ArticlePageTitle(){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(contentAlignment = Alignment.Center){
            Icon(painterResource(id = R.drawable.ic_launcher_background)  , contentDescription = "android icon",
                tint = Color(0xFF063142), modifier = Modifier
                    .size(15.dp).clip(shape = CircleShape),
                //color = Color(0xFF50E090)
            )
            Icon(painterResource(id = R.drawable.ic_launcher_foreground) , contentDescription = "android icon",
                modifier = Modifier
                    .size(25.dp),
                tint = Color(0xFF3DDC84))

        }

        Column {
            Text(text = "Published in:", style = MaterialTheme.typography.labelMedium)
            Text(text = "Android Developers",style = MaterialTheme.typography.labelMedium)
        }
    }
}