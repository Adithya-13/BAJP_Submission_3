package com.extcode.project.jetpacksubmission3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.MovieAppRepository
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity

class FavoriteViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getFavoriteMovies(sort: String): LiveData<PagedList<MovieEntity>> =
        movieAppRepository.getFavoriteMovies(sort)

    fun getFavoriteTvShows(sort: String): LiveData<PagedList<MovieEntity>> =
        movieAppRepository.getFavoriteTvShows(sort)

    fun setFavorite(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        movieAppRepository.setCourseFavorite(movieEntity, newState)
    }
}

