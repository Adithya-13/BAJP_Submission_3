package com.extcode.project.jetpacksubmission3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.data.source.local.room.MovieDao
import com.extcode.project.jetpacksubmission3.utils.SortUtils

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mMovieDao.getMovies(query)
    }

    fun getAllTvShows(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryTvShows(sort)
        return mMovieDao.getTvShows(query)
    }

    fun getMovie(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovie(movieId)
    fun getTvShow(tvShowId: Int): LiveData<MovieEntity> = mMovieDao.getTvShow(tvShowId)

    fun getAllBookmarkedMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryBookmarkedMovies(sort)
        return mMovieDao.getBookmarkedMovies(query)
    }

    fun getAllBookmarkedTvShows(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryBookmarkedTvShows(sort)
        return mMovieDao.getBookmarkedTvShows(query)
    }

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)
    fun updateMovie(movie: MovieEntity) = mMovieDao.updateMovie(movie)
    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        mMovieDao.updateMovie(movie)
    }
}