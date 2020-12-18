package com.extcode.project.jetpacksubmission3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities where isTvShow = 0")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities where isTvShow = 1")
    fun getTvShows(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieEntities where id = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieEntities where id = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movieEntities where bookmarked = 1 and isTvShow = 0")
    fun getBookmarkedMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities where bookmarked = 1 and isTvShow = 1")
    fun getBookmarkedTvShows(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}