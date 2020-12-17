package com.extcode.project.jetpacksubmission3.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.extcode.project.jetpacksubmission3.data.source.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity

class MoviesViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieAppRepository.getAllMovies()

}