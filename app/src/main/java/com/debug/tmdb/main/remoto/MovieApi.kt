package com.debug.tmdb.main.remoto

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") tmdbToken: String): BaseResponse<List<MovieResponse>>

    @GET("list/{list_id}")
    suspend fun getComicBookMovies(@Path("list_id") listId: Int, @Query("api_key") tmdbToken: String): ListMoviesResponse<List<ComicBookMoviesResponse>>
}