package com.extcode.project.jetpacksubmission3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.vo.Resource

interface MovieAppDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<MovieEntity>>

    fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>>

    fun getBookmarkedTvShows(): LiveData<PagedList<MovieEntity>>

    fun setCourseBookmark(movie: MovieEntity, state: Boolean)

}