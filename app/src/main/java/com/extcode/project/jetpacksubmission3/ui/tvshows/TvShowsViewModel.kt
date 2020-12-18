package com.extcode.project.jetpacksubmission3.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.vo.Resource

class TvShowsViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<MovieEntity>>> = movieAppRepository.getAllTvShows()

}