package com.example.jetnews.respository.video

import com.example.jetnews.model.Video
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

interface VideoRepository  {
    fun addVideo(video: Video):Unit
}