package com.example.jetnews.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.jetnews.R
import com.example.jetnews.model.Article

@Composable
fun ArticlesData(){
    val articles = listOf(
        Article(title= "Locale changes and the AndroidViewModel antipattern",
        imageResource = painterResource(id = R.drawable.post_4),
        writer = "Jose Alcerreca",
        date="April 02",
        readingTimeSpend = 1,
        isMarked = false,
        isTopArticle = true,
        isRead = false,
    ),
        Article(title = "A Little Thing about Android Module Paths",
            imageResource = painterResource(id = R.drawable.post_1_thumb),
            writer = "Pietro Maggi",
            readingTimeSpend = 1,
            isTopArticle = false,
            date = "April 02",
            isMarked = true,
            isRead = false,),
        Article(title = "Dagger in Kotlin:Gotchas and Optimizations",
            imageResource = painterResource(id = R.drawable.post_2_thumb),
            writer = "Manuel Vivo",
            readingTimeSpend = 3,
            isTopArticle = false,
            date = "April 02",
            isMarked = true,
            isRead = true,),
        Article(title = "From Java Programming Language to Kotlin -- the idiomatic way",
            imageResource = painterResource(id = R.drawable.post_3_thumb),
            writer = "Florina Muntenescu",
            readingTimeSpend = 1,
            isTopArticle = false,
            date = "April 02",
            isMarked = false,
            isRead = true,),
        Article(title = "Collections and sequences in Kotlin",
            imageResource = painterResource(id = R.drawable.post_5),
            writer = "Florina Muntenescu",
            readingTimeSpend = 4,
            isTopArticle = false,
            date = "July 24",
            isMarked = false,
            isRead = false,),
        Article(title = "Locale changes and the AndroidViewModel antipattern",
            imageResource = painterResource(id = R.drawable.post_5),
            writer = "Jose Alcerra",
            readingTimeSpend = 1,
            isTopArticle = false,
            date = "July 24",
            isMarked = false,
            isRead =false,),
    )
}