package com.example.moviesapp.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.API.MoviesApi
import com.example.moviesapp.Model.Movies
import com.example.moviesapp.Model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
    var topRatedMovies: MutableLiveData<List<Result>> = MutableLiveData()
    var LoadError: MutableLiveData<Boolean> = MutableLiveData()
    var Loading: MutableLiveData<Boolean> = MutableLiveData()

    //Getter
    fun getTop_RatedMovies(apiKey: String): LiveData<List<Result>> = topRatedMovies
    fun getError(): LiveData<Boolean> = LoadError
    fun getLoading(): LiveData<Boolean> = Loading

    private val moviesApi: MoviesApi = MoviesApi()

    //Setter
    fun loadMovies() {
        Loading.value = true
        val apiCall = moviesApi.getTop_RatedMovies("4dcdb81b0222dcc00d764b4d9e2fd20a")
        apiCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                println("Error")
                LoadError.value = true
                Loading.value = false

            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                println("RESPONSE ${response.body().toString()}")
                response.isSuccessful.let {
                    Loading.value = false
                    val resultList: List<Result> = response.body()?.results ?: emptyList()
                    topRatedMovies.value = resultList
                }
            }

        })
    }
}