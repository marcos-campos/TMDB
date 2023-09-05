package com.debug.tmdb.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.debug.tmdb.R
import com.debug.tmdb.main.remoto.ComicBookMoviesResponse
import com.squareup.picasso.Picasso

class ComicBookMovieAdapter(
    val context: Context,
    val comicBookMovieList: MutableList<ComicBookMoviesResponse>
): RecyclerView.Adapter<ComicBookMovieAdapter.ComicBookMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicBookMovieViewHolder {
        val comicBookDetailsItem =
            LayoutInflater.from(context).inflate(R.layout.comic_book_detail, parent, false)

        return ComicBookMovieViewHolder(comicBookDetailsItem)
    }

    override fun onBindViewHolder(holder: ComicBookMovieAdapter.ComicBookMovieViewHolder, position: Int) {

        val baseUrl = "https://image.tmdb.org/t/p/"
        val imageSize = "w500/"
        val posterUrl = comicBookMovieList[position].posterPath

        val logoComicBookMovies = holder.logoComicBook
        Picasso.with(context).load(baseUrl + imageSize + posterUrl).fit().into(logoComicBookMovies)
    }

    override fun getItemCount(): Int = comicBookMovieList.size

    inner class ComicBookMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val logoComicBook = itemView.findViewById<ImageView>(R.id.comic_book_detail_logo_movie)
    }
}


