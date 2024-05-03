package com.example.jetnews.respository.video

import android.util.Log
import com.example.jetnews.model.Video
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore
) : VideoRepository{
    override fun addVideo(video: Video): Unit {
        val mappedVideo = Video(
            id = video.id,
            thumbnail = video.thumbnail,
            videoUrl = video.videoUrl,
            title = video.title,
            description = video.description,
            topic = video.topic,
            createTime = video.createTime
        ).toMap()

        db.collection("video_histories")
            .add(mappedVideo)
            .addOnSuccessListener {
                Log.d("video add","success")
            }
            .addOnFailureListener{e->
                Log.d("video add", "error"+ e.toString())

            }
    }

}