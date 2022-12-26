package com.example.media_app.media

import android.media.MediaPlayer
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.Event.*

class MediaLifecycleObserver : LifecycleEventObserver {

    var player: MediaPlayer? = MediaPlayer()

    fun play() {
        player?.setOnPreparedListener {
            it.start()
        }
        player?.prepareAsync()
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            ON_PAUSE -> player?.pause()
            ON_STOP -> {
                player?.release()
                player = null
            }
            ON_DESTROY -> source.lifecycle.removeObserver(this)
            else -> Unit
        }
    }


}