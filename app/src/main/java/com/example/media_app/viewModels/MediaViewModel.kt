package com.example.media_app.viewModels

import androidx.lifecycle.ViewModel
import com.example.media_app.repository.MediaRepository

class MediaViewModel : ViewModel() {
    private val repository = MediaRepository()
    val album = repository.albumLiveData

    init {
       repository.getAlbum()
    }




}