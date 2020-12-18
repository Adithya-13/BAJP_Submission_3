package com.extcode.project.jetpacksubmission3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity

class FavoriteViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>> =
        movieAppRepository.getBookmarkedMovies()

    fun getBookmarkedTvShows(): LiveData<PagedList<MovieEntity>> =
        movieAppRepository.getBookmarkedTvShows()

    fun setBookmark(movieEntity: MovieEntity) {
        val newState = !movieEntity.bookmarked
        movieAppRepository.setCourseBookmark(movieEntity, newState)
    }
}

