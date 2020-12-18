package com.extcode.project.jetpacksubmission3.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.vo.Resource

class MoviesViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieAppRepository.getAllMovies()

}