package com.example.jetnews.ui.interests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnews.ui.theme.JetNewsTheme

@Composable
fun SelectTopicButton(
    modifier: Modifier = Modifier,
    selected:Boolean = false
){
    val icon = if (selected) Icons.Filled.Done else Icons.Filled.Add
    val iconColor = if(selected){
        MaterialTheme.colorScheme.onPrimary
    }else{
        MaterialTheme.colorScheme.primary
    }
    val borderColor = if(selected){
        MaterialTheme.colorScheme.primary
    }else{
        MaterialTheme.colorScheme.onSurface
    }
    val backgroundColor = if(selected){
        MaterialTheme.colorScheme.primary
    }else{
        MaterialTheme.colorScheme.onPrimary
    }
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        border = BorderStroke(1.dp,borderColor),
        modifier = Modifier.size(36.dp,36.dp)
    ) {
       Image(imageVector = icon,
           colorFilter = ColorFilter.tint(iconColor),
           contentDescription = null,
           modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
private fun SelectTopicButtonPreviewTemplate(
    selected:Boolean = false
){
    JetNewsTheme {
        Surface {
            SelectTopicButton(
                modifier = Modifier.padding(32.dp),
            )
        }
    }
}