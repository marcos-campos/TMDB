package com.debug.tmdb.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debug.tmdb.BuildConfig
import com.debug.tmdb.R
import com.debug.tmdb.main.remoto.MovieResponse
import com.debug.tmdb.main.remoto.ServiceProvider


class HomeFragment : Fragment() {

    private val recyclerMovies by lazy { view?.findViewById<RecyclerView>(R.id.home_recycler) }
    private val movieList = mutableListOf<MovieResponse>()
    private var adapterMovieDetail: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()

        recyclerMovies?.layoutManager = LinearLayoutManager(context)

        adapterMovieDetail = context?.let { context ->
            MoviesAdapter(context, movieList) }

        recyclerMovies?.adapter = adapterMovieDetail
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getMovies() {
        Thread {
            val result = ServiceProvider.service.getMovies(BuildConfig.tmdbToken).execute()
            if (result.isSuccessful) {
                val data = result.body()?.results

                requireActivity().runOnUiThread {
                    data?.let { listMovieResponse ->
                        movieList.addAll(listMovieResponse)
                        adapterMovieDetail?.notifyDataSetChanged()
                    }
                }
            }
        }.start()
    }
}