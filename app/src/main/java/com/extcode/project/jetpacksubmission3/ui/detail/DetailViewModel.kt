package com.extcode.project.jetpacksubmission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.extcode.project.jetpacksubmission3.data.source.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity

class DetailViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun selectedMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun selectedTvShowId(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovieDetail(): LiveData<MovieEntity> =
        movieAppRepository.getMovieById(movieId)

    fun getTvShowDetail(): LiveData<MovieEntity> =
        movieAppRepository.getTvShowById(tvShowId)

}