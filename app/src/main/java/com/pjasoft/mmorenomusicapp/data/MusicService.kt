package com.pjasoft.mmorenomusicapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: Int): Album
}

    object RetrofitClient {
        private const val BASE_URL = "https://musicapi.pjasoft.com/api/"

        val instance: MusicService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicService::class.java)

    }

}