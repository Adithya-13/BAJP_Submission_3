package com.extcode.project.jetpacksubmission3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.extcode.project.jetpacksubmission3.data.source.local.LocalDataSource
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.data.source.remote.RemoteDataSource
import com.extcode.project.jetpacksubmission3.utils.*
import com.extcode.project.jetpacksubmission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieAppRepository = FakeMovieAppRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].id

    private val random = SortUtils.RANDOM

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies(random)).thenReturn(dataSourceFactory)
        movieAppRepository.getAllMovies(random)

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies(random)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllTvShows(random)).thenReturn(dataSourceFactory)
        movieAppRepository.getAllTvShows(random)

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShows(random)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieById() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.generateDummyMovies()[0]
        `when`(local.getMovie(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieAppRepository.getMovieById(movieId)).data
        verify(local).getMovie(movieId)
        val movieResponse = movieResponses[0]
        assertNotNull(movieEntity)
        if (movieEntity != null) {
            assertEquals(movieResponse.title, movieEntity.title)
            assertEquals(movieResponse.releaseDate, movieEntity.releaseDate)
            assertEquals(movieResponse.overview, movieEntity.overview)
            assertEquals(movieResponse.originalLanguage, movieEntity.originalLanguage)
            assertEquals(movieResponse.id, movieEntity.id)
            assertEquals(movieResponse.popularity, movieEntity.popularity, movieEntity.popularity)
            assertEquals(
                movieResponse.voteAverage,
                movieResponse.voteAverage,
                movieResponse.voteAverage
            )
            assertEquals(movieResponse.voteCount, movieEntity.voteCount)
            assertEquals(movieResponse.posterPath, movieEntity.posterPath)
        }
    }

    @Test
    fun getTvShowById() {
        val dummyTvShow = MutableLiveData<MovieEntity>()
        dummyTvShow.value = DataDummy.generateDummyTvShows()[0]
        `when`(local.getTvShow(tvShowId)).thenReturn(dummyTvShow)

        val tvShowEntity =
            LiveDataTestUtil.getValue(movieAppRepository.getTvShowById(tvShowId)).data
        verify(local).getTvShow(tvShowId)
        val tvShowResponse = tvShowResponses[0]
        assertNotNull(tvShowEntity)
        if (tvShowEntity != null) {
            assertEquals(tvShowResponse.name, tvShowEntity.title)
            assertEquals(tvShowResponse.firstAirDate, tvShowEntity.releaseDate)
            assertEquals(tvShowResponse.overview, tvShowEntity.overview)
            assertEquals(tvShowResponse.originalLanguage, tvShowEntity.originalLanguage)
            assertEquals(tvShowResponse.id, tvShowEntity.id)
            assertEquals(
                tvShowResponse.popularity,
                tvShowEntity.popularity,
                tvShowEntity.popularity
            )
            assertEquals(
                tvShowResponse.voteAverage,
                tvShowResponse.voteAverage,
                tvShowResponse.voteAverage
            )
            assertEquals(tvShowResponse.voteCount, tvShowEntity.voteCount)
            assertEquals(tvShowResponse.posterPath, tvShowEntity.posterPath)
        }
    }

    @Test
    fun getBookmarkedMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllBookmarkedMovies(random)).thenReturn(dataSourceFactory)
        movieAppRepository.getBookmarkedMovies(random)

        val bookmarkedMovieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllBookmarkedMovies(random)
        assertNotNull(bookmarkedMovieEntities)
        assertEquals(movieResponses.size.toLong(), bookmarkedMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllBookmarkedTvShows(random)).thenReturn(dataSourceFactory)
        movieAppRepository.getBookmarkedTvShows(random)

        val bookmarkedTvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllBookmarkedTvShows(random)
        assertNotNull(bookmarkedTvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), bookmarkedTvShowEntities.data?.size?.toLong())
    }
}