package com.debug.tmdb.main.data.repository

import com.debug.tmdb.main.data.model.BaseResponse
import com.debug.tmdb.main.data.model.ComicBookMoviesResponse
import com.debug.tmdb.main.data.model.ListMoviesResponse
import com.debug.tmdb.main.data.model.MovieResponse
import com.debug.tmdb.main.data.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val service: MovieService): MovieRepository {
    override suspend fun getMovies(tmdbToken: String): Flow<BaseResponse<List<MovieResponse>>> = flow {
        val moviesList = service.getMovies(tmdbToken = tmdbToken)
        emit(moviesList)
    }

    override suspend fun getComicBookMovies(
        listId: Int,
        tmdbToken: String
    ): Flow<ListMoviesResponse<List<ComicBookMoviesResponse>>> = flow{
        val comicBookMoviesList = service.getComicBookMovies(1, tmdbToken = tmdbToken)
        emit(comicBookMoviesList)
    }
}