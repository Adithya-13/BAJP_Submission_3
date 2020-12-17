package com.extcode.project.jetpacksubmission3.service

import com.extcode.project.jetpacksubmission3.BuildConfig
import com.extcode.project.jetpacksubmission3.data.source.remote.response.MoviesResponse
import com.extcode.project.jetpacksubmission3.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MoviesResponse>

    @GET("tv")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowsResponse>

}