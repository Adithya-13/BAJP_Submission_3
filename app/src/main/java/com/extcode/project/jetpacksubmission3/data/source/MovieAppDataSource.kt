package com.extcode.project.jetpacksubmission3.data.source

import androidx.lifecycle.LiveData
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity

interface MovieAppDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<MovieEntity>>

    fun getTvShowById(tvShowId: String): LiveData<MovieEntity>

}