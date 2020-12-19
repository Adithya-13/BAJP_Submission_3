package com.extcode.project.jetpacksubmission3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.vo.Resource

interface MovieAppDataSource {

    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(sort: String): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(sort: String): LiveData<PagedList<MovieEntity>>

    fun setCourseFavorite(movie: MovieEntity, state: Boolean)

}