package com.debug.tmdb.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debug.tmdb.BuildConfig
import com.debug.tmdb.R
import com.debug.tmdb.main.remoto.MovieResponse
import com.debug.tmdb.main.remoto.ComicBookMoviesResponse
import com.debug.tmdb.main.remoto.ServiceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.net.HttpURLConnection


class HomeFragment : Fragment() {

    private val recyclerMovies by lazy { view?.findViewById<RecyclerView>(R.id.home_recycler_movies) }
    private val recyclerComicBookMovies by lazy { view?.findViewById<RecyclerView>(R.id.home_recycler_comic_book_movies) }
    private val movieList = mutableListOf<MovieResponse>()
    private val comicBookMovieList = mutableListOf<ComicBookMoviesResponse>()
    private var adapterMovieDetail: MoviesAdapter? = null
    private var adapterComicBookDetail: ComicBookMovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
        getComicBookMovies()
        configMoviesAdapter()
        configComicBookMoviesAdapter()
    }

    private fun configMoviesAdapter() {
        recyclerMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapterMovieDetail = context?.let { context ->
            MoviesAdapter(context, movieList)
        }

        recyclerMovies?.adapter = adapterMovieDetail
    }

    private fun configComicBookMoviesAdapter() {
        recyclerComicBookMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapterComicBookDetail = context?.let { context ->
            ComicBookMovieAdapter(context, comicBookMovieList) }

        recyclerComicBookMovies?.adapter = adapterComicBookDetail
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getMovies() {
        lifecycleScope.launch(Dispatchers.IO){

            try{
                val value = ServiceProvider.service.getMovies(BuildConfig.tmdbToken)

                withContext(Dispatchers.Main){
                    movieList.addAll(value.results)
                    adapterMovieDetail?.notifyDataSetChanged()
                }
            } catch (exception: HttpException) {
                when(exception.code()){
                    HttpURLConnection.HTTP_BAD_REQUEST -> {
                        //error 400
                    }
                    HttpURLConnection.HTTP_NOT_FOUND -> {
                        //error 404
                    }
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                        //error 500
                    }
                    else -> {
                        //some other error
                    }
                }

            } catch (exception: Exception){
                Log.e("error", exception.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getComicBookMovies() {
        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val value = ServiceProvider.service.getComicBookMovies(1, BuildConfig.tmdbToken)

                withContext(Dispatchers.Main) {
                    comicBookMovieList.addAll(value.items)
                    adapterComicBookDetail?.notifyDataSetChanged()
                }
            } catch (exception: HttpException) {
                when(exception.code()){
                    HttpURLConnection.HTTP_BAD_REQUEST -> {
                        //error 400
                    }
                    HttpURLConnection.HTTP_NOT_FOUND -> {
                        //error 404
                    }
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                        //error 500
                    }
                    else -> {
                        //some other error
                    }
                }

            } catch (exception: Exception){
                Log.e("error", exception.toString())
            }

        }
    }
}