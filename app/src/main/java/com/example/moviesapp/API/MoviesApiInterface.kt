package com.example.moviesapp.API

import com.example.moviesapp.Model.Movies
import com.example.moviesapp.Model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApiInterface {
    @GET("top_rated")
    fun getTop_RatedMovies(
        @Query("api_key")apiKey:String
    ):Call<Movies>
}