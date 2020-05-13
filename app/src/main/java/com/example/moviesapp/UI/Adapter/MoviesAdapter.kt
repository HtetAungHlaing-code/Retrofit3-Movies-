package com.example.moviesapp.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Model.Result
import com.example.moviesapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var resultList: List<Result> = listOf()

    fun updateResultlist(resultList: List<Result>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindMovies(result: Result) {
            itemView.txtMovie_title.text = result.title
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w200${result.poster_path}")
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.movie_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        )

    override fun getItemCount(): Int =
        resultList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindMovies(resultList[position])
    }
}