package com.example.media_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.media_app.adapter.MediaListener
import com.example.media_app.adapter.MusicAdapter
import com.example.media_app.constans.BASE_URL
import com.example.media_app.databinding.ActivityMainBinding
import com.example.media_app.media.MediaLifecycleObserver
import com.example.media_app.viewModels.MediaViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MediaViewModel = MediaViewModel()
    private val mediaObserver = MediaLifecycleObserver()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recycler = binding.rcView
        adapter = MusicAdapter(viewModel.album.value?.title.toString(), object : MediaListener {

            override fun loadMedia(fileName: String) {

                if (mediaObserver.musicNow == fileName) {
                    if (!mediaObserver.playState) {
                        mediaObserver.mediaPlayer?.start()
                        mediaObserver.playState = true
                    } else {
                        mediaObserver.playState = false
                        mediaObserver.mediaPlayer?.pause()

                    }

                } else {
                    mediaObserver.mediaPlayer?.reset()
                    mediaObserver.apply {
                        playState = true
                        mediaPlayer?.setDataSource("${BASE_URL}${fileName}")
                    }.play()
                    mediaObserver.musicNow = fileName
                }

            }

            override fun editTrack(index: Int, id: Int) {
              viewModel.editTrack(index , id)
            }


        })

        recycler.adapter = adapter

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
                    progressBar.visibility = View.GONE
                }
                adapter.submitList(it.tracks)
            }

        }

        setContentView(binding.root)
    }
}