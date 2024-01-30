package com.debug.tmdb.main.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.debug.tmdb.R
import com.debug.tmdb.main.data.model.MovieResponse
import com.squareup.picasso.Picasso

class MoviesAdapter(
    val context: Context,
    val movieList: MutableList<MovieResponse>
): RecyclerView.Adapter<MoviesAdapter.MovieDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsViewHolder {
        val movieDetailsItem =
            LayoutInflater.from(context).inflate(R.layout.movie_detail, parent, false)

        return MovieDetailsViewHolder(movieDetailsItem)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {

        val baseUrl = "https://image.tmdb.org/t/p/"
        val imageSize = "w500/"
        val posterUrl = movieList[position].posterPath

        val moviesImages = holder.movieImage
        Picasso.with(context).load(baseUrl + imageSize + posterUrl).fit().into(moviesImages)

        holder.movieDate.text = movieList[position].releaseDate
    }

    inner class MovieDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieDate = itemView.findViewById<TextView>(R.id.movie_detail_date_movie)
        val movieImage = itemView.findViewById<ImageView>(R.id.movie_detail_image_movie)

    }
}