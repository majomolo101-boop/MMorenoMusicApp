package com.pjasoft.mmorenomusicapp.services

import com.pjasoft.mmorenomusicapp.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: Int): Album
}