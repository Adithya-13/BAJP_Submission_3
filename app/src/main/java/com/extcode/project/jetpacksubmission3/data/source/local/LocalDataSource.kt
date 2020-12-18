package com.extcode.project.jetpacksubmission3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovies()
    fun getAllTvShows(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getTvShows()
    fun getMovie(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovie(movieId)
    fun getTvShow(tvShowId: Int): LiveData<MovieEntity> = mMovieDao.getTvShow(tvShowId)
    fun getAllBookmarkedMovies(): DataSource.Factory<Int, MovieEntity> =
        mMovieDao.getBookmarkedMovies()

    fun getAllBookmarkedTvShows(): DataSource.Factory<Int, MovieEntity> =
        mMovieDao.getBookmarkedTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)
    fun updateMovie(movie: MovieEntity) = mMovieDao.updateMovie(movie)
    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        mMovieDao.updateMovie(movie)
    }
}