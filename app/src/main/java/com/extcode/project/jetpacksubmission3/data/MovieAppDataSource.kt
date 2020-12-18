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

    fun getBookmarkedMovies(sort: String): LiveData<PagedList<MovieEntity>>

    fun getBookmarkedTvShows(sort: String): LiveData<PagedList<MovieEntity>>

    fun setCourseBookmark(movie: MovieEntity, state: Boolean)

}