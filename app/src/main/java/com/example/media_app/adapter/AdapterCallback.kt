package com.example.media_app.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.media_app.model.TrackModel


class AdapterCallback : DiffUtil.ItemCallback<TrackModel>() {
    override fun areItemsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
        return oldItem == newItem
    }
}