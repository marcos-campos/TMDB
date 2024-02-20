package com.debug.tmdb.main.presenter.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debug.tmdb.R
import com.debug.tmdb.main.presenter.adapter.ComicBookMovieAdapter
import com.debug.tmdb.main.presenter.adapter.MoviesAdapter
import com.debug.tmdb.main.data.model.MovieResponse
import com.debug.tmdb.main.data.model.ComicBookMoviesResponse
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val recyclerMovies by lazy { view?.findViewById<RecyclerView>(R.id.home_recycler_movies) }
    private val recyclerComicBookMovies by lazy { view?.findViewById<RecyclerView>(R.id.home_recycler_comic_book_movies) }
    private val movieList = mutableListOf<MovieResponse>()
    private val comicBookMovieList = mutableListOf<ComicBookMoviesResponse>()
    private var adapterMovieDetail: MoviesAdapter? = null
    private var adapterComicBookDetail: ComicBookMovieAdapter? = null
    private val homeViewModel: HomeViewModel by viewModel()


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

        homeViewModel.getMovies()
        homeViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            movieList.addAll(it)
            adapterMovieDetail?.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getComicBookMovies() {

        homeViewModel.getComicBooksMovies()
        homeViewModel.comicBookMoviesLiveData.observe(viewLifecycleOwner, Observer {
            comicBookMovieList.addAll(it)
            adapterComicBookDetail?.notifyDataSetChanged()
        })
    }
}