package com.example.media_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.media_app.model.AlbumModel
import com.example.media_app.repository.MediaRepository
import java.text.FieldPosition

class MediaViewModel : ViewModel() {
    private val repository = MediaRepository()
    val album: LiveData<AlbumModel>
        get() {
            return repository.albumLiveData
        }

    init {
        repository.getAlbum()
    }

    fun editTrack(index: Int, id: Int) {
        repository.editTrack(index , id)
    }


}