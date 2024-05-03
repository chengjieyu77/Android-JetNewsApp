package com.example.jetnews.model

import java.util.Date

data class Video(
    val thumbnail:String,
    val videoUrl:String,
    val title:String,
    val description:String,
    val createTime:String,
    val topic:String,
    val id:String,
){
    fun toMap():MutableMap<String,Any>{
        return mutableMapOf(
            "thumbnail" to this.thumbnail,
            "video_url" to this.videoUrl,
            "quote" to this.title,
            "description" to this.description,
            "createTime" to this.createTime,
            "video_id" to this.id,
            "topic" to this.topic,
        )
    }
}