package com.debug.tmdb.main.data.repository

import com.debug.tmdb.main.data.model.BaseResponse
import com.debug.tmdb.main.data.model.ComicBookMoviesResponse
import com.debug.tmdb.main.data.model.ListMoviesResponse
import com.debug.tmdb.main.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(tmdbToken: String): Flow<BaseResponse<List<MovieResponse>>>

    suspend fun getComicBookMovies(listId: Int, tmdbToken: String): Flow<ListMoviesResponse<List<ComicBookMoviesResponse>>>
}