package com.debug.tmdb.main.remoto

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.themoviedb.org/3/"
private val mediaType = "application/json".toMediaType()

object ServiceProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(MovieApi::class.java)
}