package com.debug.tmdb.main.presenter.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debug.tmdb.BuildConfig
import com.debug.tmdb.main.data.model.ComicBookMoviesResponse
import com.debug.tmdb.main.data.model.MovieResponse
import com.debug.tmdb.main.data.repository.ServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val serviceProvider = ServiceProvider
    val moviesLiveData = MutableLiveData<List<MovieResponse>>()
    val comicBookMoviesLiveData = MutableLiveData<List<ComicBookMoviesResponse>>()


    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            serviceProvider.service.getMovies(BuildConfig.tmdbToken).let {
                it.results?.let {movies ->
                    moviesLiveData.postValue(movies)
                }
            }
        }
    }

    fun getComicBooksMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            serviceProvider.service.getComicBookMovies(1, BuildConfig.tmdbToken).let {
                it.items.let {comicBookMovies ->
                    comicBookMoviesLiveData.postValue(comicBookMovies)
                }
            }
        }
    }
}