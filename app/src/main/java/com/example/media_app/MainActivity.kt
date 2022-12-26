package com.example.media_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.media_app.adapter.MediaListener
import com.example.media_app.adapter.MusicAdapter
import com.example.media_app.databinding.ActivityMainBinding
import com.example.media_app.media.MediaLifecycleObserver
import com.example.media_app.viewModels.MediaViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MediaViewModel = MediaViewModel()
    private val mediaObserver = MediaLifecycleObserver()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recycler = binding.rcView

        lifecycle.addObserver(mediaObserver)

        binding.buttonPlay.setOnClickListener {


        }

        viewModel.album.observe(this) {
            if (it.id != -1) {
                with(binding) {
                    albumName.text = it.title
                    authorName.text = it.artist
                    published.text = it.published
                    genre.text = it.genre
                    recycler.adapter = MusicAdapter(it.tracks, it.title, object : MediaListener {
                        override fun playMedia() {
                            super.playMedia()
                        }

                    })
                    progressBar.visibility = View.GONE
                }
            }

        }

        setContentView(binding.root)
    }
}