package com.example.jetnews.widgets

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetnews.R
import com.example.jetnews.screens.interests.InterestContent
import kotlin.math.max


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(
    title:@Composable ()->Unit={},//String = "jetnews",
    isMainScreen:Boolean = true,
    navController: NavController,
    onAddActionClicked:()->Unit = {},
    onButtonClicked:()->Unit={}
){
    CenterAlignedTopAppBar(
        title = title,
//    {
//        Text(text = title,
//            style = MaterialTheme.typography.headlineMedium,
//            maxLines = 1,
//        )}
        navigationIcon = {
            if (isMainScreen){
                Icon(painter = painterResource(id = R.drawable.ic_jetnews_logo), contentDescription = null,
                    //tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .clickable { onButtonClicked.invoke() })
            }else{
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                    //tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .clickable { onButtonClicked.invoke() })
            }

        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon"
                    )
                }
            } else {
                Box {}
            }
        },
        modifier = Modifier.background(color = Color.Transparent),


        )
}