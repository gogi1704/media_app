package com.example.media_app.model

data class AlbumModel (
    val id:Int ,
    val title:String,
    val subTittle:String?,
    val artist:String,
    val published:String,
    val genre:String,
    val tracks:List<TrackModel>
        )