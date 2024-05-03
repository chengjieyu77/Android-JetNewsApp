package com.example.jetnews.model

import androidx.compose.ui.graphics.painter.Painter
import java.util.Date

data class Article (
    val title:String,
    val imageResource: Painter,
    val writer:String,
    val readingTimeSpend:Int,
    val date: String,
    val isMarked:Boolean,
    val isTopArticle:Boolean,
    val isRead:Boolean
    )