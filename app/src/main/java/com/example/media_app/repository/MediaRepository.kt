package com.example.media_app.repository

import androidx.lifecycle.MutableLiveData
import com.example.media_app.constans.LIST_ALBUM_URL
import com.example.media_app.model.AlbumModel
import com.example.media_app.model.TrackModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MediaRepository {
    private val client = OkHttpClient.Builder()
        .build()
    val gson = Gson()
    var album = AlbumModel(-1, "", "", "", " ", "", emptyList())
        set(value) {
            field = value
            albumLiveData.postValue(value)
        }
    val albumLiveData = MutableLiveData(album)


    fun getAlbum() {
        val request = Request.Builder()
            .url(LIST_ALBUM_URL)
            .get()
            .build()
        try {
            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val jsonObject = response.body?.string()
                        album = gson.fromJson(jsonObject, AlbumModel::class.java)
                    } else throw Exception()

                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun editTrack(index: Int, id: Int) {
        val tracks = album.tracks
        tracks.find { it.id == id }?.isPlay = !album.tracks[index].isPlay
        album = album.copy(tracks = tracks)

    }


}