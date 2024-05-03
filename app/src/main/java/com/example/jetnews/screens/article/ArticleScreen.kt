package com.example.jetnews.screens.article

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetnews.R
import com.example.jetnews.components.ArticlePageTitle
import com.example.jetnews.model.Article
import com.example.jetnews.widgets.NewsAppBar

@Preview
@Composable
fun ArticleScreen(navController: NavController = rememberNavController() ) {
    val article =
        Article(title= "Locale changes and the AndroidViewModel antipattern",
        imageResource = painterResource(id = R.drawable.post_4),
        writer = "Jose Alcerreca",
        date="April 02",
        readingTimeSpend = 1,
        isMarked = false,
        isTopArticle = true,
        isRead = false,
    )

    Scaffold(
        topBar = { NewsAppBar(
            navController = navController,
            title = {
                ArticlePageTitle()
            },
            isMainScreen = false,
        ){
            navController.popBackStack()
        } },
        bottomBar = { BottomAppBar(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ) {
            //thumb up
            IconButton(onClick = { 
                Log.d("thumb up button","clicked to thumb up")
            }) {
                Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "thumb up icon",
                    modifier = Modifier.size(25.dp))
            }
            //mark
            IconButton(onClick = { 
                Log.d("mark icon button", "click me to mark the article") 
            }) {
                Icon(painter = painterResource(id = R.drawable.bookmark), contentDescription ="mark icon",
                    modifier = Modifier.size(25.dp))
            }
            //share
            IconButton(onClick = { 
                Log.d("share icon","click me to share to other places")
            }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "share icon",
                    modifier = Modifier.size(25.dp))
            }
            //change font
            IconButton(onClick = { 
                Log.d("font icon","click me to change font")
            }) {
                Icon(painterResource(id = R.drawable.font), contentDescription = "font icon",
                    modifier = Modifier.size(25.dp))
            }
        }}
    ) {
        ArticleContent(navController = navController,modifier = Modifier.padding(it),article = article)

    }
}

@Composable
fun ArticleContent(navController: NavController, modifier: Modifier, article: Article) {
    Column(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Image(painter = painterResource(id = R.drawable.post_1), contentDescription = "post picture")
        Text(text = article.title, style = MaterialTheme.typography.displaySmall,
            maxLines = 3,modifier = Modifier.padding(start=16.dp,end=16.dp))
        //articles description fake data only for appearance
        //Text(text = article.description)
        Text(text = "Locale changes and the Android viewmodel! This article Locale changes and the Android viewmodel! This article",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start=16.dp,end=16.dp))

        //writer row
        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp,bottom=16.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "account icon ")
            Column {
                Text(text = article.writer, fontWeight = FontWeight.Bold)
                Text(text = article.date + "- ${article.readingTimeSpend} min read",color = Color.LightGray)
            }
        }

        //main content fake data only for expression
        //Text(text = article.maincontent)
        Text(text = "main content main content main content main content main content main content",
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 20.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    }


}
