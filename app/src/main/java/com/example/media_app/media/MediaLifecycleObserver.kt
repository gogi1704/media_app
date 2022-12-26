package com.example.media_app.media

import android.media.MediaPlayer
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.Event.*

class MediaLifecycleObserver : LifecycleEventObserver {

    var playState = false
    var musicNow = ""

    var mediaPlayer: MediaPlayer? = MediaPlayer()

    fun play() {
        mediaPlayer?.setOnPreparedListener {
            it.start()
        }
        mediaPlayer?.prepareAsync()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            ON_PAUSE -> {
                mediaPlayer?.pause()
            }
            ON_STOP -> {
                mediaPlayer?.release()
            }
            ON_DESTROY -> source.lifecycle.removeObserver(this)

            else -> Unit
        }
    }


}