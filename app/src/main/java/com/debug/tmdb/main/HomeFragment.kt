package com.debug.tmdb.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.debug.tmdb.BuildConfig
import com.debug.tmdb.R
import com.debug.tmdb.main.remoto.ServiceProvider


class HomeFragment : Fragment() {

    val movieTitle by lazy { view?.findViewById<TextView>(R.id.home_tv_welcome) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
    }

    private fun getMovies() {
        Thread {
            val result = ServiceProvider.service.getMovies(BuildConfig.tmdbToken).execute()

            if (result.isSuccessful) {
                val data = result.body()?.results

                requireActivity().runOnUiThread {
                    movieTitle?.text = data?.get(1)?.title
                }
            }
        }.start()
    }
}