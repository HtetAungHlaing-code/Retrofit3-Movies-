package com.example.moviesapp.API

import com.example.moviesapp.Model.Movies
import com.example.moviesapp.Model.Result
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApi {
    private val moviesApiInterface: MoviesApiInterface

    companion object{
        const val BASE_URL="https://api.themoviedb.org/3/movie/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        moviesApiInterface = retrofit.create(MoviesApiInterface::class.java)
    }
    fun getTop_RatedMovies(apiKey: String):Call<Movies>{
        return moviesApiInterface.getTop_RatedMovies(apiKey)
    }
}
