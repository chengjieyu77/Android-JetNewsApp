package com.example.jetnews.screens.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnews.model.Video
import com.example.jetnews.respository.video.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: VideoRepository
): ViewModel(){
    fun addVideo(video: Video) = viewModelScope.launch {
        repository.addVideo(video = video)
    }

}