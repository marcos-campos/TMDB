package com.debug.tmdb.main.presenter.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debug.tmdb.BuildConfig
import com.debug.tmdb.main.data.model.ComicBookMoviesResponse
import com.debug.tmdb.main.data.model.MovieResponse
import com.debug.tmdb.main.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository): ViewModel() {
    val moviesLiveData = MutableLiveData<List<MovieResponse>>()
    val comicBookMoviesLiveData = MutableLiveData<List<ComicBookMoviesResponse>>()

    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getMovies(BuildConfig.tmdbToken).collect(){
                it.results?.let {movies ->
                    moviesLiveData.postValue(movies)
                }
            }
        }
    }
    fun getComicBooksMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getComicBookMovies(1, BuildConfig.tmdbToken).collect() {
                it.items.let {comicBookMovies ->
                    comicBookMoviesLiveData.postValue(comicBookMovies)
                }
            }
        }
    }
}