package com.extcode.project.jetpacksubmission3.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.extcode.project.jetpacksubmission3.data.source.remote.response.Movie
import com.extcode.project.jetpacksubmission3.data.source.remote.response.MoviesResponse
import com.extcode.project.jetpacksubmission3.data.source.remote.response.TvShow
import com.extcode.project.jetpacksubmission3.data.source.remote.response.TvShowsResponse
import com.extcode.project.jetpacksubmission3.service.ApiConfig
import com.extcode.project.jetpacksubmission3.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        ApiConfig.getApiService().getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val results = response.body()?.results
                if (results != null) {
                    resultMovies.postValue(ApiResponse.success(results))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(RemoteDataSource::class.simpleName, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShow>>>()
        ApiConfig.getApiService().getTvShows().enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                val results = response.body()?.results
                if (results != null) {
                    resultTvShows.postValue(ApiResponse.success(results))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(RemoteDataSource::class.simpleName, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

}