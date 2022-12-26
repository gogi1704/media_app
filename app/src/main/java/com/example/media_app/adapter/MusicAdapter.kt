package com.example.media_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.media_app.databinding.ItemLayoutBinding
import com.example.media_app.model.TrackModel

interface MediaListener{
    fun playMedia(){}
}

class MusicAdapter(private val list: List<TrackModel>, private val album: String , val listener:MediaListener) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding , )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(list, album = album , listener)
    }

    override fun getItemCount(): Int = list.size


    class MusicViewHolder(private val binding: ItemLayoutBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: List<TrackModel>, album: String , listener:MediaListener) {
            with(binding) {
                textNameMusic.text = list[adapterPosition].file
                nameAlbum.text = album

            }
        }

    }
}