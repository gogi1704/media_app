package com.example.media_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.media_app.databinding.ItemLayoutBinding
import com.example.media_app.model.TrackModel

interface MediaListener {
    fun loadMedia(fileName: String) {}
    fun editTrack(index:Int , id:Int)
}

class MusicAdapter(
    private val album: String,
    private val listener: MediaListener
) :
    ListAdapter<TrackModel, MusicAdapter.MusicViewHolder>(AdapterCallback()) {

    class MusicViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: String, item: TrackModel, listener: MediaListener , position: Int) {
            with(binding) {
                imagePlay.isChecked = item.isPlay
                textNameMusic.text = item.file
                nameAlbum.text = album
                imagePlay.setOnClickListener {
                    listener.loadMedia(item.file)
                    listener.editTrack(position , item.id)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(album = album, item, listener = listener , position)
    }
}